package com.timetracking.timetrackingapi.repository;

import com.timetracking.timetrackingapi.domain.PeriodoTotalDia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PeriodoDiaRepository extends JpaRepository<PeriodoTotalDia, Long> {

    Optional<PeriodoTotalDia> findAllByUsuarioIdAndDia(@Param("idUsuario") Long idUsuario,
                                                       @Param("dia")LocalDate dia);

    List<PeriodoTotalDia> findAllByUsuarioIdAndDiaBetween(@Param("idUsuario") Long idUsuario,
                                                          @Param("dataInicio")LocalDate dataInicio,
                                                          @Param("dataFim")LocalDate dataFim);

}
