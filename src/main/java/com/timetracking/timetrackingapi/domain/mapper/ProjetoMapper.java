package com.timetracking.timetrackingapi.domain.mapper;

import com.timetracking.timetrackingapi.domain.Projeto;
import com.timetracking.timetrackingapi.domain.dto.ProjetoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjetoMapper {

    ProjetoMapper INSTANCE = Mappers.getMapper( ProjetoMapper.class );

    ProjetoDTO paraProjetoDTO(Projeto projeto);

    List<ProjetoDTO> paraProjetosDTOList (List<Projeto> projetos);

}
