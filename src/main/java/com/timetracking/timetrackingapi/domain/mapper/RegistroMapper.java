package com.timetracking.timetrackingapi.domain.mapper;

import com.timetracking.timetrackingapi.domain.Registro;
import com.timetracking.timetrackingapi.domain.dto.RegistroDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegistroMapper {

    @Mapping(source = "tipoPeriodo.id", target = "tipoPeriodo")
    @Mapping(source = "tipoRegistro.id", target = "tipoRegistro")
    @Mapping(source = "usuario.id", target = "usuario")
    @Mapping(source = "dataHorario", target = "data", dateFormat = "dd/MM/yyyy")
    @Mapping(source = "dataHorario", target = "horario", dateFormat = "HH:mm")
    RegistroDTO paraRegistroDTO(Registro registro);

    List<RegistroDTO> paraRegistrosDTOList(List<Registro> registros);

}
