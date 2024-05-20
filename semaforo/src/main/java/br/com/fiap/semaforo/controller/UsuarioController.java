package br.com.fiap.semaforo.controller;

import br.com.fiap.semaforo.dto.UsuarioAtualizacaoDTO;
import br.com.fiap.semaforo.dto.UsuarioCadastroDTO;
import br.com.fiap.semaforo.dto.UsuarioExibicaoDTO;
import br.com.fiap.semaforo.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDTO salvar(@RequestBody @Valid UsuarioCadastroDTO usuarioCadastroDTO) {
        return usuarioService.salvar(usuarioCadastroDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioExibicaoDTO> listarTodos(@PageableDefault(size = 2) Pageable paginacao) {
        return usuarioService.listarTodos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioExibicaoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        usuarioService.excluir(id);
    }

    @PutMapping
    public ResponseEntity<UsuarioExibicaoDTO> atualizar(@RequestBody @Valid UsuarioAtualizacaoDTO usuarioAtualizacaoDTO) {
        return ResponseEntity.ok(usuarioService.atualizar(usuarioAtualizacaoDTO));
    }
}
