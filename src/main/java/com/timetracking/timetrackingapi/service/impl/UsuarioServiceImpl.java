package com.timetracking.timetrackingapi.service.impl;

import com.timetracking.timetrackingapi.domain.Usuario;
import com.timetracking.timetrackingapi.domain.dto.UsuarioDTO;
import com.timetracking.timetrackingapi.domain.mapper.UsuarioMapper;
import com.timetracking.timetrackingapi.repository.UsuarioRepository;
import com.timetracking.timetrackingapi.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;

    private UsuarioMapper usuarioMapper;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarioMapper.paraUsuariosDTOList(usuarios);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = Usuario.builder()
                .nome(usuarioDTO.getNome())
                .build();

        usuarioRepository.saveAndFlush(usuario);

        return usuarioMapper.paraUsuarioDTO(usuario);
    }
}
