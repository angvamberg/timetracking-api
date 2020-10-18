package com.timetracking.timetrackingapi.repository;

import com.timetracking.timetrackingapi.domain.PeriodoDia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PeriodoDiaRepository extends JpaRepository<PeriodoDia, Long> {

    Optional<PeriodoDia> findAllByUsuarioIdAndDia(@Param("idUsuario") Long idUsuario,
                                                  @Param("dia")LocalDate dia);

}
