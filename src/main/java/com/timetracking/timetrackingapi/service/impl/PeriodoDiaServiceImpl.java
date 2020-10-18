package com.timetracking.timetrackingapi.service.impl;

import com.timetracking.timetrackingapi.domain.PeriodoDia;
import com.timetracking.timetrackingapi.domain.Registro;
import com.timetracking.timetrackingapi.domain.Usuario;
import com.timetracking.timetrackingapi.domain.dto.PeriodoCompletoDiaDTO;
import com.timetracking.timetrackingapi.repository.PeriodoDiaRepository;
import com.timetracking.timetrackingapi.service.PeriodoDiaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.timetracking.timetrackingapi.service.util.DataUtil.calcularMinutosEntreDuasDatas;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class PeriodoDiaServiceImpl implements PeriodoDiaService {

    private PeriodoDiaRepository periodoDiaRepository;

    @Override
    public PeriodoCompletoDiaDTO construirPeriodoDiaDTOPorRegistros(List<Registro> registros) {
        if (registros.isEmpty())
            return null;

        PeriodoCompletoDiaDTO periodoCompletoDiaDTO = PeriodoCompletoDiaDTO.builder().build();

        registros.forEach(registro -> {
            if (registro.getTipoRegistro().isEntrada()) {
                if (registro.getTipoPeriodo().isMatutino()) {
                    periodoCompletoDiaDTO.setEntradaManha(registro.getDataHorario());
                    setarDia(periodoCompletoDiaDTO, registro.getDataHorario());
                } else if (registro.getTipoPeriodo().isVespertino()) {
                    periodoCompletoDiaDTO.setEntradaTarde(registro.getDataHorario());
                    setarDia(periodoCompletoDiaDTO, registro.getDataHorario());
                }
            }

            if (registro.getTipoRegistro().isSaida()) {
                if (registro.getTipoPeriodo().isMatutino()) {
                    periodoCompletoDiaDTO.setSaidaManha(registro.getDataHorario());
                    setarDia(periodoCompletoDiaDTO, registro.getDataHorario());
                } else if (registro.getTipoPeriodo().isVespertino()) {
                    periodoCompletoDiaDTO.setSaidaTarde(registro.getDataHorario());
                    setarDia(periodoCompletoDiaDTO, registro.getDataHorario());
                }
            }
        });

        calcularTotalMinutosNoDia(periodoCompletoDiaDTO);

        return periodoCompletoDiaDTO;
    }

    private void setarDia(PeriodoCompletoDiaDTO periodoCompletoDiaDTO, LocalDateTime dataHorario) {
        if (isNull(periodoCompletoDiaDTO.getDia()))
            periodoCompletoDiaDTO.setDia(dataHorario.toLocalDate());
    }

    private void calcularTotalMinutosNoDia(PeriodoCompletoDiaDTO periodoCompletoDiaDTO) {
        long minutos = 0L;
        if (nonNull(periodoCompletoDiaDTO.getEntradaManha()) && nonNull(periodoCompletoDiaDTO.getSaidaManha())) {
            minutos += calcularMinutosEntreDuasDatas(periodoCompletoDiaDTO.getEntradaManha(), periodoCompletoDiaDTO.getSaidaManha());
        }

        if (nonNull(periodoCompletoDiaDTO.getEntradaTarde()) && nonNull(periodoCompletoDiaDTO.getSaidaTarde())) {
            minutos += calcularMinutosEntreDuasDatas(periodoCompletoDiaDTO.getEntradaTarde(), periodoCompletoDiaDTO.getSaidaTarde());
        }

        periodoCompletoDiaDTO.setTotalMinutosDoDia(minutos);
    }

    @Override
    public PeriodoDia criarPeriodoDiaPorPeriodoCompletoDTO(PeriodoCompletoDiaDTO periodoCompletoDiaDTO) {
        return PeriodoDia.builder()
                .dia(periodoCompletoDiaDTO.getDia())
                .totalMinutosDoDia(periodoCompletoDiaDTO.getTotalMinutosDoDia())
                .usuario(Usuario.builder().id(periodoCompletoDiaDTO.getUsuario().getId()).build())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public PeriodoDia criarOuBuscarPeriodoDiaAlterandoTotalMinutos(PeriodoCompletoDiaDTO periodoCompletoDiaDTO) {
        Optional<PeriodoDia> periodoDia = periodoDiaRepository.findAllByUsuarioIdAndDia(periodoCompletoDiaDTO.getUsuario().getId(),
                periodoCompletoDiaDTO.getDia());

        if (periodoDia.isPresent()) {
            periodoDia.get().setTotalMinutosDoDia(periodoCompletoDiaDTO.getTotalMinutosDoDia());
            return periodoDia.get();
        }

        return criarPeriodoDiaPorPeriodoCompletoDTO(periodoCompletoDiaDTO);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PeriodoDia salvarPeriodoDia(PeriodoDia periodoDia) {
        if (isNull(periodoDia))
            return null;

        return periodoDiaRepository.saveAndFlush(periodoDia);
    }

}
