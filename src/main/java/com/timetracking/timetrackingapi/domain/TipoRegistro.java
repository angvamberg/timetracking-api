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

    public static final Integer ENTRADA = 1;
    public static final Integer SAIDA = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_TP_REGISTRO")
    private Integer id;

    @Column(name = "DESCRICAO", unique = true)
    private String descricao;

    public boolean isEntrada() {
        return this.getId().equals(ENTRADA);
    }

    public boolean isSaida() {
        return this.getId().equals(SAIDA);
    }

}
