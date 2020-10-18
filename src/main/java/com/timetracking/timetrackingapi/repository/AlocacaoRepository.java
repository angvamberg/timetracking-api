package com.timetracking.timetrackingapi.repository;

import com.timetracking.timetrackingapi.domain.Alocacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AlocacaoRepository extends JpaRepository<Alocacao, Long> {

    List<Alocacao> findAllByUsuarioIdAndAndDiaAlocacao(@Param("idUsuario") Long idUsuario,
                                                       @Param("diaAlocacao") LocalDate diaAlocacao);

}
