package com.timetracking.timetrackingapi.service.impl;

import com.timetracking.timetrackingapi.domain.*;
import com.timetracking.timetrackingapi.domain.dto.CadastroRegistroDTO;
import com.timetracking.timetrackingapi.domain.dto.PeriodoCompletoDiaDTO;
import com.timetracking.timetrackingapi.domain.dto.RegistroDTO;
import com.timetracking.timetrackingapi.domain.dto.UsuarioDTO;
import com.timetracking.timetrackingapi.domain.mapper.RegistroMapper;
import com.timetracking.timetrackingapi.repository.RegistroRepository;
import com.timetracking.timetrackingapi.service.*;
import com.timetracking.timetrackingapi.service.util.exception.ValidacaoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

import static com.timetracking.timetrackingapi.domain.Registro.TOTAL_MAX_REGISTROS_POR_DIA;
import static com.timetracking.timetrackingapi.domain.TipoPeriodo.MATUTINO;
import static com.timetracking.timetrackingapi.domain.TipoPeriodo.VESPERTINO;
import static com.timetracking.timetrackingapi.domain.TipoRegistro.SAIDA;
import static com.timetracking.timetrackingapi.service.util.DataUtil.*;
import static com.timetracking.timetrackingapi.service.util.MessageLoader.getMessage;
import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class RegistroServiceImpl implements RegistroService {

    private UsuarioService usuarioService;
    private TipoPeriodoService tipoPeriodoService;
    private TipoRegistroService tipoRegistroService;
    private PeriodoDiaService periodoDiaService;

    private RegistroRepository registroRepository;

    private RegistroMapper registroMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RegistroDTO salvarRegistro(CadastroRegistroDTO cadastroRegistroDTO) {
        final Usuario usuario = usuarioService.obterUsuarioPorId(cadastroRegistroDTO.getUsuario());
        final TipoPeriodo tipoPeriodo = tipoPeriodoService.obterTipoPeriodoPorId(cadastroRegistroDTO.getTipoPeriodo());
        final TipoRegistro tipoRegistro = tipoRegistroService.obterTipoRegistroPorId(cadastroRegistroDTO.getTipoRegistro());
        final Registro registro = criarRegistro(cadastroRegistroDTO, usuario, tipoPeriodo, tipoRegistro);
        final List<Registro> registrosJaCadastradosParaODia = obterRegistrosDeUmPeriodoEUsuario(usuario, registro);

        validarSeRegistroEhFinalDeSemana(registro);
        validarQuantidadeMaximaDeRegistrosParaODia(registrosJaCadastradosParaODia);
        validarSeJaExisteRegistroParaOPeriodo(registro, registrosJaCadastradosParaODia);
        validarSeHoraEntradaEhMaiorQueHoraSaida(registro, registrosJaCadastradosParaODia);
        PeriodoCompletoDiaDTO periodoDiaDTO = validarSeHaTempoMinimoDeAlmoco(registro, registrosJaCadastradosParaODia);
        validarSeRegistroSobrepoePeriodo(registro, periodoDiaDTO);

        registroRepository.saveAndFlush(registro);
        PeriodoDia periodoDia = periodoDiaService.criarOuBuscarPeriodoDia(periodoDiaDTO);
        periodoDia.setTotalMinutosDoDia(periodoDiaDTO.getTotalMinutosDoDia());
        periodoDiaService.salvarPeriodoDia(periodoDia);

        return registroMapper.paraRegistroDTO(registro);
    }

    private void validarSeRegistroSobrepoePeriodo(Registro registro, PeriodoCompletoDiaDTO periodoCompletoDiaDTO) {
        if (registro.getTipoPeriodo().isMatutino()) {
            Integer count = registroRepository.countAllByDataHorarioBetweenAndUsuarioIdAndTipoPeriodoId(registro.getDataHorario().with(LocalTime.MIN),
                    registro.getDataHorario().with(LocalTime.MAX), periodoCompletoDiaDTO.getUsuario().getId(), VESPERTINO);

            if (count.equals(2) && registro.getDataHorario().isAfter(periodoCompletoDiaDTO.getEntradaTarde()) &&
                registro.getDataHorario().isBefore(periodoCompletoDiaDTO.getSaidaTarde())) {
                    throw new ValidacaoException(getMessage("msg.erro.registro.06"));
            }
        }

        if (registro.getTipoPeriodo().isVespertino()) {
            Integer count = registroRepository.countAllByDataHorarioBetweenAndUsuarioIdAndTipoPeriodoId(registro.getDataHorario().with(LocalTime.MIN),
                    registro.getDataHorario().with(LocalTime.MAX), periodoCompletoDiaDTO.getUsuario().getId(), MATUTINO);

            if (count.equals(2) && registro.getDataHorario().isAfter(periodoCompletoDiaDTO.getEntradaManha()) &&
                        registro.getDataHorario().isBefore(periodoCompletoDiaDTO.getSaidaManha())) {
                    throw new ValidacaoException(getMessage("msg.erro.registro.06"));
            }
        }
    }

    private PeriodoCompletoDiaDTO validarSeHaTempoMinimoDeAlmoco(Registro registro, List<Registro> registrosJaCadastradosParaODia) {
        registrosJaCadastradosParaODia.add(registro);
        PeriodoCompletoDiaDTO periodoCompletoDiaDTO = periodoDiaService.construirPeriodoDiaDTOPorRegistros(registrosJaCadastradosParaODia);
        periodoCompletoDiaDTO.setUsuario(UsuarioDTO.builder().id(registro.getUsuario().getId()).build());

        if (nonNull(periodoCompletoDiaDTO.getSaidaManha()) && nonNull(periodoCompletoDiaDTO.getEntradaTarde())) {
            long minutos = calcularMinutosEntreDuasDatas(periodoCompletoDiaDTO.getSaidaManha(), periodoCompletoDiaDTO.getEntradaTarde());
            if (minutos < 60)
                throw new ValidacaoException(getMessage("msg.erro.registro.05"));
        }

        return periodoCompletoDiaDTO;
    }

    private void validarSeHoraEntradaEhMaiorQueHoraSaida(Registro registro, List<Registro> registrosJaCadastradosParaODia) {
        if (registro.getTipoRegistro().isEntrada() && !registrosJaCadastradosParaODia.isEmpty()) {
            registrosJaCadastradosParaODia
                    .stream()
                    .filter(registroCadastrado -> registroCadastrado.getTipoRegistro().getId().equals(SAIDA) &&
                            registroCadastrado.getTipoPeriodo().equals(registro.getTipoPeriodo()))
                    .forEach(registroCadastrado -> {
                        if (registroCadastrado.getDataHorario().isBefore(registro.getDataHorario()))
                            throw new ValidacaoException(getMessage("msg.erro.registro.03"));
                    });
        }
    }

    private List<Registro> obterRegistrosDeUmPeriodoEUsuario(Usuario usuario, Registro registro) {
        return registroRepository
                .findAllByDataHorarioBetweenAndUsuarioId(registro.getDataHorario().with(LocalTime.MIN),
                        registro.getDataHorario().with(LocalTime.MAX), usuario.getId());
    }

    private void validarQuantidadeMaximaDeRegistrosParaODia(List<Registro> registrosJaCadastradosParaODia) {
        if (registrosJaCadastradosParaODia.size() == TOTAL_MAX_REGISTROS_POR_DIA)
            throw new ValidacaoException(getMessage("msg.erro.registro.04"));
    }

    private void validarSeRegistroEhFinalDeSemana(Registro registro) {
        if (ehFinalDeSemana(registro.getDataHorario()))
            throw new ValidacaoException(getMessage("msg.erro.registro.02"));
    }

    private void validarSeJaExisteRegistroParaOPeriodo(Registro registro, List<Registro> registrosJaCadastradosParaODia) {
        registrosJaCadastradosParaODia.forEach(registroCadastrado -> {
            if (registroCadastrado.getTipoPeriodo().getId().equals(registro.getTipoPeriodo().getId())
            && registroCadastrado.getTipoRegistro().getId().equals(registro.getTipoRegistro().getId()))
                throw new ValidacaoException(getMessage("msg.erro.registro.01"));
        });
    }

    private Registro criarRegistro(CadastroRegistroDTO cadastroRegistroDTO, Usuario usuario,
                                   TipoPeriodo tipoPeriodo, TipoRegistro tipoRegistro) {
        return Registro.builder()
                .usuario(usuario)
                .tipoRegistro(tipoRegistro)
                .tipoPeriodo(tipoPeriodo)
                .dataHorario(transformarStringDataHoraParaLocalDateTime(cadastroRegistroDTO.getData() + " " +
                        cadastroRegistroDTO.getHorario()))
                .build();
    }
}
