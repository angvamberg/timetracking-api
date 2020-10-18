package com.timetracking.timetrackingapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RelatorioDTO {

    private String mesAno;
    private Long usuario;
    private String horasNecessarias;
    private String horasTrabalhadas;
    private String horasDevidas;

}
