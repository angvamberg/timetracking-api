package com.timetracking.timetrackingapi.service;

import com.timetracking.timetrackingapi.domain.Usuario;
import com.timetracking.timetrackingapi.domain.dto.CadastroUsuarioDTO;
import com.timetracking.timetrackingapi.domain.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {

    List<UsuarioDTO> listarUsuarios();

    UsuarioDTO salvarUsuario(CadastroUsuarioDTO cadastroUsuarioDTO);

    Usuario obterUsuarioPorId(Long id);

}
