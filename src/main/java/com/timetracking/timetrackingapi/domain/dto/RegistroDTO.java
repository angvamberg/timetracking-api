package com.timetracking.timetrackingapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RegistroDTO {

    private Long id;
    private String data;
    private String horario;
    private Long tipoPeriodo;
    private Long tipoRegistro;
    private Long usuario;

}
