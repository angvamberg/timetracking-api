package com.timetracking.timetrackingapi.repository;

import com.timetracking.timetrackingapi.domain.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {

}
