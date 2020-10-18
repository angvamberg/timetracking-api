package com.timetracking.timetrackingapi.repository;

import com.timetracking.timetrackingapi.domain.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {

    List<Registro> findAllByDataHorarioBetweenAndUsuarioId(@Param("dataInicio") LocalDateTime dataInicio,
                                                           @Param("dataFim") LocalDateTime dataFim,
                                                           @Param("idUsuario") Long idUsuario);

    Integer countAllByDataHorarioBetweenAndUsuarioIdAndTipoPeriodoId(@Param("dataInicio") LocalDateTime dataInicio,
                                                                     @Param("dataFim") LocalDateTime dataFim,
                                                                     @Param("idUsuario") Long idUsuario,
                                                                     @Param("tipoPeriodo")Integer tipoPeriodo);
}
