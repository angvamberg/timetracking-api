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

    public static final Integer MATUTINO = 1;
    public static final Integer VESPERTINO = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_TP_PERIODO")
    private Integer id;

    @Column(name = "DESCRICAO", unique = true)
    private String descricao;

    public boolean isMatutino() {
        return this.getId().equals(MATUTINO);
    }

    public boolean isVespertino() {
        return this.getId().equals(VESPERTINO);
    }

}
