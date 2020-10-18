package com.timetracking.timetrackingapi.service.impl;

import com.timetracking.timetrackingapi.domain.Alocacao;
import com.timetracking.timetrackingapi.domain.PeriodoTotalDia;
import com.timetracking.timetrackingapi.domain.Projeto;
import com.timetracking.timetrackingapi.domain.Usuario;
import com.timetracking.timetrackingapi.domain.dto.AlocacaoDTO;
import com.timetracking.timetrackingapi.domain.dto.CadastroAlocacaoDTO;
import com.timetracking.timetrackingapi.domain.mapper.AlocacaoMapper;
import com.timetracking.timetrackingapi.repository.AlocacaoRepository;
import com.timetracking.timetrackingapi.service.AlocacaoService;
import com.timetracking.timetrackingapi.service.PeriodoDiaService;
import com.timetracking.timetrackingapi.service.util.exception.ValidacaoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.timetracking.timetrackingapi.service.util.DataUtil.transformarStringDataParaLocalDate;
import static com.timetracking.timetrackingapi.service.util.DataUtil.transformarStringHorasEmMinutos;
import static com.timetracking.timetrackingapi.service.util.MessageLoader.getMessage;
import static com.timetracking.timetrackingapi.service.util.MessageLoader.mensagemNaoEncontrado;
import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class AlocacaoServiceImpl implements AlocacaoService {

    private AlocacaoRepository alocacaoRepository;

    private AlocacaoMapper alocacaoMapper;

    private PeriodoDiaService periodoDiaService;

    @Override
    @Transactional(readOnly = true)
    public List<AlocacaoDTO> listarAlocacoes() {
        return alocacaoMapper.paraAlocacoesDTOList(alocacaoRepository.findAll());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public AlocacaoDTO salvarAlocacao(CadastroAlocacaoDTO cadastroAlocacaoDTO) {
        Alocacao alocacao = criarAlocacao(cadastroAlocacaoDTO);
        List<Alocacao> alocacoesJaRealizadas = alocacaoRepository.findAllByUsuarioIdAndAndDiaAlocacao(alocacao.getUsuario().getId(), alocacao.getDiaAlocacao());
        PeriodoTotalDia periodoTotalDia = periodoDiaService.buscarPeriodoDia(alocacao.getUsuario().getId(), alocacao.getDiaAlocacao());

        if (nonNull(periodoTotalDia)) {
            validarSePossuiSaldoParaAlocar(alocacao, periodoTotalDia);
            validarSeTotalHorasExtrapolamSaldo(alocacao, periodoTotalDia, alocacoesJaRealizadas);
            return alocacaoMapper.paraAlocacaoDTO(alocacaoRepository.saveAndFlush(alocacao));
        } else {
            throw mensagemNaoEncontrado("Periodo Total do Dia");
        }
    }

    private void validarSePossuiSaldoParaAlocar(Alocacao alocacao, PeriodoTotalDia periodoTotalDia) {
        if (alocacao.getTempoAlocadoEmMinutos() > periodoTotalDia.getTotalMinutosDoDia())
            throw new ValidacaoException(getMessage("msg.erro.alocacao.02"));
    }

    private void validarSeTotalHorasExtrapolamSaldo(Alocacao alocacao, PeriodoTotalDia periodoTotalDia, List<Alocacao> alocacoesJaRealizadas) {
        long somaDeHorasJaRegistradas = somarHorasPorAlocacoes(alocacoesJaRealizadas);
        if ((alocacao.getTempoAlocadoEmMinutos() + somaDeHorasJaRegistradas) > periodoTotalDia.getTotalMinutosDoDia())
            throw new ValidacaoException(getMessage("msg.erro.alocacao.01"));
    }

    private long somarHorasPorAlocacoes(List<Alocacao> alocacoesJaRealizadas) {
        long horasAcumuladas = 0;

        for (Alocacao alocacao : alocacoesJaRealizadas)
            horasAcumuladas += alocacao.getTempoAlocadoEmMinutos();

        return horasAcumuladas;
    }

    private Alocacao criarAlocacao(CadastroAlocacaoDTO cadastroAlocacaoDTO) {
        return Alocacao.builder()
                .diaAlocacao(transformarStringDataParaLocalDate(cadastroAlocacaoDTO.getDiaAlocacao()))
                .projeto(Projeto.builder().id(cadastroAlocacaoDTO.getProjeto()).build())
                .usuario(Usuario.builder().id(cadastroAlocacaoDTO.getUsuario()).build())
                .tempoAlocadoEmMinutos(transformarStringHorasEmMinutos(cadastroAlocacaoDTO.getTempoAlocado()))
                .build();
    }
}
