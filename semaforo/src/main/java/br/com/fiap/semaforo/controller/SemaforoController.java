package br.com.fiap.semaforo.controller;

import br.com.fiap.semaforo.dto.SemaforoAtualizacaoDTO;
import br.com.fiap.semaforo.dto.SemaforoCadastroDTO;
import br.com.fiap.semaforo.dto.SemaforoExibicaoDTO;
import br.com.fiap.semaforo.service.SemaforoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/semaforo")
public class SemaforoController {

    @Autowired
    private SemaforoService semaforoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SemaforoExibicaoDTO salvar(@RequestBody @Valid SemaforoCadastroDTO semaforoCadastroDTO) {
        return semaforoService.salvar(semaforoCadastroDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<SemaforoExibicaoDTO> listarTodos(@PageableDefault(size = 2) Pageable paginacao) {
        return semaforoService.listarTodos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SemaforoExibicaoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(semaforoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        semaforoService.excluir(id);
    }

    @PutMapping
    public ResponseEntity<SemaforoExibicaoDTO> atualizar(@RequestBody @Valid SemaforoAtualizacaoDTO semaforoAtualizacaoDTO) {
        return ResponseEntity.ok(semaforoService.atualizar(semaforoAtualizacaoDTO));
    }
}
