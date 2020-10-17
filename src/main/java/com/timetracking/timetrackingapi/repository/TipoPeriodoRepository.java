package com.timetracking.timetrackingapi.repository;

import com.timetracking.timetrackingapi.domain.TipoPeriodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPeriodoRepository extends JpaRepository<TipoPeriodo, Long> {

}
