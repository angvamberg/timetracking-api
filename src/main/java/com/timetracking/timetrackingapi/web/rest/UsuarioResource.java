package com.timetracking.timetrackingapi.web.rest;

import com.timetracking.timetrackingapi.domain.dto.CadastroUsuarioDTO;
import com.timetracking.timetrackingapi.domain.dto.UsuarioDTO;
import com.timetracking.timetrackingapi.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("usuarios")
public class UsuarioResource {

    private UsuarioService usuarioService;

    @GetMapping
    @ApiOperation(value = "Retorna uma listagem com os usuários cadastrados")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @PostMapping
    @ApiOperation(value = "Cadastra um novo usuário")
    public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody CadastroUsuarioDTO cadastroUsuarioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.criarUsuario(cadastroUsuarioDTO));
    }

}
