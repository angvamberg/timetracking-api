package com.timetracking.timetrackingapi.web.rest;

import com.timetracking.timetrackingapi.domain.dto.AlocacaoDTO;
import com.timetracking.timetrackingapi.domain.dto.CadastroAlocacaoDTO;
import com.timetracking.timetrackingapi.domain.dto.MensagemDTO;
import com.timetracking.timetrackingapi.service.AlocacaoService;
import com.timetracking.timetrackingapi.service.util.exception.ValidacaoException;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("alocacoes")
public class AlocacaoResource {

    private AlocacaoService alocacaoService;

    @GetMapping
    @ApiOperation(value = "Retorna uma listagem com as alocacoes cadastradas")
    public ResponseEntity<List<AlocacaoDTO>> listarAlocacoes() {
        return ResponseEntity.ok(alocacaoService.listarAlocacoes());
    }

    @PostMapping
    @ApiOperation(value = "Cadastra uma nova alocacao")
    public ResponseEntity<?> salvarAlocacao(@RequestBody CadastroAlocacaoDTO cadastroAlocacaoDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(alocacaoService.salvarAlocacao(cadastroAlocacaoDTO));
        } catch (ValidacaoException validaE) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MensagemDTO.builder().mensagem(validaE.getMessage()).build());
        }
    }

}
