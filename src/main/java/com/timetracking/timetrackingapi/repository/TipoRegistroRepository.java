package com.timetracking.timetrackingapi.repository;

import com.timetracking.timetrackingapi.domain.TipoRegistro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRegistroRepository extends JpaRepository<TipoRegistro, Long> {

}
