package com.timetracking.timetrackingapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PeriodoCompletoDiaDTO {

    private LocalDateTime entradaManha;
    private LocalDateTime saidaManha;
    private LocalDateTime entradaTarde;
    private LocalDateTime saidaTarde;
    private LocalDate dia;
    private UsuarioDTO usuario;
    private Long totalMinutosDoDia;

}
