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
@Table(schema = "TIMETRACKING", name = "TIPO_REGISTRO")
public class TipoRegistro {

    public static final int ENTRADA = 1;
    public static final int SAIDA = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_TP_REGISTRO")
    private Long id;

    @Column(name = "DESCRICAO", unique = true)
    private String descricao;

}
