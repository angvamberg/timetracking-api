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
@Table(schema = "TIMETRACKING", name = "PROJETO")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_PROJETO")
    private Long id;

    @Column(name = "DESCRICAO", unique = true)
    private String descricao;

}
