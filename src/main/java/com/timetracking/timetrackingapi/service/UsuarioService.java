package com.timetracking.timetrackingapi.service;

import com.timetracking.timetrackingapi.domain.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {

    List<UsuarioDTO> listarUsuarios();

    UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO);

}