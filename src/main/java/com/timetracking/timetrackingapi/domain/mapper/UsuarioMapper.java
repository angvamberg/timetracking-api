package com.timetracking.timetrackingapi.domain.mapper;

import com.timetracking.timetrackingapi.domain.Usuario;
import com.timetracking.timetrackingapi.domain.dto.UsuarioDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO paraUsuarioDTO(Usuario usuario);

    List<UsuarioDTO> paraUsuariosDTOList(List<Usuario> usuarios);

}
