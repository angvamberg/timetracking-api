package com.timetracking.timetrackingapi.repository;

import com.timetracking.timetrackingapi.domain.Alocacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlocacaoRepository extends JpaRepository<Alocacao, Long> {

}
