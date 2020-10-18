package com.timetracking.timetrackingapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CadastroRegistroDTO {

    private String data;
    private String horario;
    private Integer tipoPeriodo;
    private Integer tipoRegistro;
    private Long usuario;

}
