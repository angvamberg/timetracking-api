package com.timetracking.timetrackingapi.service;

import com.timetracking.timetrackingapi.domain.PeriodoTotalDia;
import com.timetracking.timetrackingapi.domain.Registro;
import com.timetracking.timetrackingapi.domain.dto.PeriodoCompletoDiaDTO;

import java.time.LocalDate;
import java.util.List;

public interface PeriodoDiaService {

    PeriodoCompletoDiaDTO construirPeriodoDiaDTOPorRegistros(List<Registro> registros);

    PeriodoTotalDia criarPeriodoDiaPorPeriodoCompletoDTO(PeriodoCompletoDiaDTO periodoCompletoDiaDTO);

    PeriodoTotalDia criarOuBuscarPeriodoDia(PeriodoCompletoDiaDTO periodoCompletoDiaDTO);

    PeriodoTotalDia buscarPeriodoDia(Long idUsuario, LocalDate dia);

    PeriodoTotalDia salvarPeriodoDia(PeriodoTotalDia periodoTotalDia);

}
