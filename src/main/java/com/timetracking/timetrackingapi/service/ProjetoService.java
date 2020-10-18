package com.timetracking.timetrackingapi.service;

import com.timetracking.timetrackingapi.domain.dto.CadastroProjetoDTO;
import com.timetracking.timetrackingapi.domain.dto.ProjetoDTO;

import java.util.List;

public interface ProjetoService {

    List<ProjetoDTO> listarProjetos();

    ProjetoDTO criarProjeto(CadastroProjetoDTO cadastroProjetoDTO);

}
