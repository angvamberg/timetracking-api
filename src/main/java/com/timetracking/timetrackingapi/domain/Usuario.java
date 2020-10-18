package com.timetracking.timetrackingapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "TIMETRACKING", name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type="uuid-char")
    @Column(name="CD_USUARIO", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
    private String id;

    @Column(name = "NOME")
    private String nome;

}
