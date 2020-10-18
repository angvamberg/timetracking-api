package com.timetracking.timetrackingapi.service;

import com.timetracking.timetrackingapi.domain.PeriodoDia;
import com.timetracking.timetrackingapi.domain.Registro;
import com.timetracking.timetrackingapi.domain.dto.PeriodoCompletoDiaDTO;

import java.util.List;

public interface PeriodoDiaService {

    PeriodoCompletoDiaDTO construirPeriodoDiaDTOPorRegistros(List<Registro> registros);

    PeriodoDia criarPeriodoDiaPorPeriodoCompletoDTO(PeriodoCompletoDiaDTO periodoCompletoDiaDTO);

    PeriodoDia criarOuBuscarPeriodoDiaAlterandoTotalMinutos(PeriodoCompletoDiaDTO periodoCompletoDiaDTO);

    PeriodoDia salvarPeriodoDia(PeriodoDia periodoDia);

}
