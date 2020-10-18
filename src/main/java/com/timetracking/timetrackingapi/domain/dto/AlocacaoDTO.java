package com.timetracking.timetrackingapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AlocacaoDTO {

    private Long id;
    private String tempoAlocado;
    private String diaAlocacao;
    private Long usuario;
    private Long projeto;

}
