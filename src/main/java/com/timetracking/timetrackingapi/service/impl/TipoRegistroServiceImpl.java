package com.timetracking.timetrackingapi.service.impl;

import com.timetracking.timetrackingapi.domain.TipoRegistro;
import com.timetracking.timetrackingapi.repository.TipoRegistroRepository;
import com.timetracking.timetrackingapi.service.TipoRegistroService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.timetracking.timetrackingapi.service.util.MessageLoader.mensagemNaoEncontrado;

@Service
@AllArgsConstructor
public class TipoRegistroServiceImpl implements TipoRegistroService {

    private TipoRegistroRepository tipoRegistroRepository;

    @Override
    @Transactional(readOnly = true)
    public TipoRegistro obterTipoRegistroPorId(Integer id) {
        return tipoRegistroRepository.findById(id)
                .orElseThrow(() -> mensagemNaoEncontrado("Tipo do Registro"));
    }
}
