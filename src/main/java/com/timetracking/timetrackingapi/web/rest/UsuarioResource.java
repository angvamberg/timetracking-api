package com.timetracking.timetrackingapi.web.rest;

import com.timetracking.timetrackingapi.domain.dto.CadastroUsuarioDTO;
import com.timetracking.timetrackingapi.domain.dto.MensagemDTO;
import com.timetracking.timetrackingapi.domain.dto.UsuarioDTO;
import com.timetracking.timetrackingapi.service.RegistroService;
import com.timetracking.timetrackingapi.service.UsuarioService;
import com.timetracking.timetrackingapi.service.util.exception.ValidacaoException;
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
    private RegistroService registroService;

    @GetMapping
    @ApiOperation(value = "Retorna uma listagem com os usuários cadastrados")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @PostMapping
    @ApiOperation(value = "Cadastra um novo usuário")
    public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody CadastroUsuarioDTO cadastroUsuarioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(cadastroUsuarioDTO));
    }

    @GetMapping("/{idUsuario}/registros/{mesAno}")
    @ApiOperation(value = "Retorna uma listagem com os registros do usuário a partir de um determinado mês/ano")
    public ResponseEntity<?> listarRegistrosPorUsuarioEMesAno(@PathVariable("idUsuario") Long idUsuario,
                                                              @PathVariable("mesAno") String mesAno) {
        try {
            return ResponseEntity.ok(registroService.listarRegistrosPorUsuarioEMesAno(idUsuario, mesAno));
        } catch (ValidacaoException validaE) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MensagemDTO.builder().mensagem(validaE.getMessage()).build());
        }
    }

    @GetMapping("/{idUsuario}/relatorios/{mesAno}")
    @ApiOperation(value = "Retorna uma listagem com os registros do usuário a partir de um determinado mês/ano")
    public ResponseEntity<?> obterRelatorioParaUsuarioEMesAno(@PathVariable("idUsuario") Long idUsuario,
                                                              @PathVariable("mesAno") String mesAno) {
        try {
            return ResponseEntity.ok(registroService.obterRelatorioParaUsuarioEMesAno(idUsuario, mesAno));
        } catch (ValidacaoException validaE) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MensagemDTO.builder().mensagem(validaE.getMessage()).build());
        }
    }

}
