package com.timetracking.timetrackingapi.service;

import com.timetracking.timetrackingapi.domain.dto.AlocacaoDTO;
import com.timetracking.timetrackingapi.domain.dto.CadastroAlocacaoDTO;

import java.util.List;

public interface AlocacaoService {

    List<AlocacaoDTO> listarAlocacoes();

    AlocacaoDTO salvarAlocacao(CadastroAlocacaoDTO cadastroAlocacaoDTO);

}
