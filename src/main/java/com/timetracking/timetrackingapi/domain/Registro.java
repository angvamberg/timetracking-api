package com.timetracking.timetrackingapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "TIMETRACKING", name = "REGISTRO")
public class Registro {

    public static final int TOTAL_MAX_REGISTROS_POR_DIA = 4;
    public static final int TOTAL_MAX_REGISTROS_POR_PERIODO = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_REGISTRO")
    private Long id;

    @Column(name = "DATA_HORARIO")
    private LocalDateTime dataHorario;

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
