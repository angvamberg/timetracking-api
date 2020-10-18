package com.timetracking.timetrackingapi.domain.mapper;

import com.timetracking.timetrackingapi.domain.Projeto;
import com.timetracking.timetrackingapi.domain.dto.ProjetoDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjetoMapper {

    ProjetoDTO paraProjetoDTO(Projeto projeto);

    List<ProjetoDTO> paraProjetosDTOList (List<Projeto> projetos);

}
