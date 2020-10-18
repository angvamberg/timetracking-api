package com.timetracking.timetrackingapi.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "TIMETRACKING", name = "REGISTRO")
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_REGISTRO")
    private Long id;

    @Column(name = "HORARIO")
    private LocalDateTime horario;

    @ManyToOne
    @JoinColumn(name = "CD_TP_PERIODO")
    private TipoPeriodo tipoPeriodo;

    @ManyToOne
    @JoinColumn(name = "CD_TP_REGISTRO")
    private TipoRegistro tipoRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_USUARIO")
    private Usuario usuario;

}
