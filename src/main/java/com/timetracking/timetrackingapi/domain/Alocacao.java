package com.timetracking.timetrackingapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "TIMETRACKING", name = "ALOCACAO")
public class Alocacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CD_ALOCACAO")
    private Long id;

    @Column(name = "HORAS_ALOCADAS")
    private int tempoAlocadoEmMinutos;

    @Column(name = "DIA_ALOCACAO")
    private LocalDate diaAlocacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_USUARIO")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_PROJETO")
    private Projeto projeto;

}
