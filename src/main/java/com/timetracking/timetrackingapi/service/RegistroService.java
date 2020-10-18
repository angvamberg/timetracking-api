package com.timetracking.timetrackingapi.service;

import com.timetracking.timetrackingapi.domain.dto.CadastroRegistroDTO;
import com.timetracking.timetrackingapi.domain.dto.RegistroDTO;
import com.timetracking.timetrackingapi.domain.dto.RelatorioDTO;

import java.util.List;

public interface RegistroService {

    RegistroDTO salvarRegistro(CadastroRegistroDTO cadastroRegistroDTO);

    List<RegistroDTO> listarRegistrosPorUsuarioEMesAno(Long idUsuario, String mesAno);

    RelatorioDTO obterRelatorioParaUsuarioEMesAno(Long idUsuario, String mesAno);
}
