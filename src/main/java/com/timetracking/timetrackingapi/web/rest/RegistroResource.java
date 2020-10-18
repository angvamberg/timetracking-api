package com.timetracking.timetrackingapi.web.rest;

import com.timetracking.timetrackingapi.domain.dto.CadastroRegistroDTO;
import com.timetracking.timetrackingapi.domain.dto.MensagemDTO;
import com.timetracking.timetrackingapi.service.RegistroService;
import com.timetracking.timetrackingapi.service.util.exception.ValidacaoException;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;

@RestController
@AllArgsConstructor
@RequestMapping("registros")
public class RegistroResource {

    private RegistroService registroService;

    @PostMapping
    @ApiOperation(value = "Cadastra um novo registro")
    public ResponseEntity<?> salvarRegistro(@RequestBody CadastroRegistroDTO cadastroRegistroDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(registroService.salvarRegistro(cadastroRegistroDTO));
        } catch (NoResultException | ValidacaoException validaE){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MensagemDTO.builder().mensagem(validaE.getMessage()).build());
        }
    }

}
