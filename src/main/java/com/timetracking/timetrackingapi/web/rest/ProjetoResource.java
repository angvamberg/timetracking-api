package com.timetracking.timetrackingapi.web.rest;

import com.timetracking.timetrackingapi.domain.dto.CadastroProjetoDTO;
import com.timetracking.timetrackingapi.domain.dto.ProjetoDTO;
import com.timetracking.timetrackingapi.service.ProjetoService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("projetos")
public class ProjetoResource {

    private ProjetoService projetoService;

    @GetMapping
    @ApiOperation(value = "Retorna uma listagem com os projetos cadastrados")
    public ResponseEntity<List<ProjetoDTO>> listarProjetos() {
        return ResponseEntity.ok(projetoService.listarProjetos());
    }

    @PostMapping
    @ApiOperation(value = "Cadastra um novo projeto")
    public ResponseEntity<ProjetoDTO> criarProjeto(@RequestBody CadastroProjetoDTO cadastroProjetoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projetoService.criarProjeto(cadastroProjetoDTO));
    }

}
