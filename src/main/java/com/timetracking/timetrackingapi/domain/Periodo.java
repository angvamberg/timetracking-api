package com.timetracking.timetrackingapi.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "TIMETRACKING", name = "PERIODO")
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CD_PERIODO")
    private Long id;

    @Column(name = "INICIO_MANHA")
    private LocalDateTime horarioInicioManha;

    @Column(name = "FIM_MANHA")
    private LocalDateTime horarioFimManha;

    @Column(name = "INICIO_TARDE")
    private LocalDateTime horarioInicioTarde;

    @Column(name = "FIM_TARDE")
    private LocalDateTime horarioFimTarde;

    @Column(name = "TOTAL_DIA")
    private int totalDeHorasTrabalhadas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_USUARIO")
    private Usuario usuario;

}
