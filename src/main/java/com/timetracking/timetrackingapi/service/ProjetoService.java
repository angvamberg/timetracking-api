package com.timetracking.timetrackingapi.service;

import com.timetracking.timetrackingapi.domain.dto.ProjetoDTO;

import java.util.List;

public interface ProjetoService {

    List<ProjetoDTO> listarProjetos();

    ProjetoDTO criarProjeto(ProjetoDTO projetoDTO);

}
