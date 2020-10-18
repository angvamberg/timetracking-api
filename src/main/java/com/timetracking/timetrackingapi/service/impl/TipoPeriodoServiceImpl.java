package com.timetracking.timetrackingapi.service.impl;

import com.timetracking.timetrackingapi.domain.TipoPeriodo;
import com.timetracking.timetrackingapi.repository.TipoPeriodoRepository;
import com.timetracking.timetrackingapi.service.TipoPeriodoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.timetracking.timetrackingapi.service.util.MessageLoader.mensagemNaoEncontrado;

@Service
@AllArgsConstructor
public class TipoPeriodoServiceImpl implements TipoPeriodoService {

    private TipoPeriodoRepository tipoPeriodoRepository;

    @Override
    @Transactional(readOnly = true)
    public TipoPeriodo obterTipoPeriodoPorId(Integer id) {
        return tipoPeriodoRepository.findById(id)
                .orElseThrow(() -> mensagemNaoEncontrado("Tipo do Período"));
    }
}
