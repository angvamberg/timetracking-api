package com.timetracking.timetrackingapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "TIMETRACKING", name = "TIPO_PERIODO")
public class TipoPeriodo {

    public static final int MATUTINO = 1;
    public static final int VESPERTINO = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CD_TP_PERIODO")
    private Long id;

    @Column(name = "DESCRICAO", unique = true)
    private String descricao;

}
