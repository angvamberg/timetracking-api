package com.timetracking.timetrackingapi.service.impl;

import com.timetracking.timetrackingapi.domain.Usuario;
import com.timetracking.timetrackingapi.domain.dto.CadastroUsuarioDTO;
import com.timetracking.timetrackingapi.domain.dto.RegistroDTO;
import com.timetracking.timetrackingapi.domain.dto.UsuarioDTO;
import com.timetracking.timetrackingapi.domain.mapper.UsuarioMapper;
import com.timetracking.timetrackingapi.repository.RegistroRepository;
import com.timetracking.timetrackingapi.repository.UsuarioRepository;
import com.timetracking.timetrackingapi.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.timetracking.timetrackingapi.service.util.MessageLoader.mensagemNaoEncontrado;

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
    public UsuarioDTO salvarUsuario(CadastroUsuarioDTO cadastroUsuarioDTO) {
        Usuario usuario = Usuario.builder()
                .nome(cadastroUsuarioDTO.getNome())
                .build();

        usuarioRepository.saveAndFlush(usuario);

        return usuarioMapper.paraUsuarioDTO(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario obterUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> mensagemNaoEncontrado("Usuário"));
    }

}
