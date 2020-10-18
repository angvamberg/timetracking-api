package com.timetracking.timetrackingapi.web.rest;

import com.timetracking.timetrackingapi.domain.dto.ProjetoDTO;
import com.timetracking.timetrackingapi.service.ProjetoService;
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
    public ResponseEntity<List<ProjetoDTO>> listarProjetos() {
        return ResponseEntity.ok(projetoService.listarProjetos());
    }

    @PostMapping
    public ResponseEntity<ProjetoDTO> listarProjetos(@RequestBody ProjetoDTO projetoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projetoService.criarProjeto(projetoDTO));
    }

}
