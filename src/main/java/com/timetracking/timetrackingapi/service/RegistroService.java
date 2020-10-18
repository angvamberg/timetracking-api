package com.timetracking.timetrackingapi.service;

import com.timetracking.timetrackingapi.domain.dto.CadastroRegistroDTO;
import com.timetracking.timetrackingapi.domain.dto.RegistroDTO;

public interface RegistroService {

    RegistroDTO salvarRegistro(CadastroRegistroDTO cadastroRegistroDTO);

}
