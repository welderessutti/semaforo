package br.com.fiap.semaforo.service;

import br.com.fiap.semaforo.dto.SemaforoAtualizacaoDTO;
import br.com.fiap.semaforo.dto.SemaforoCadastroDTO;
import br.com.fiap.semaforo.dto.SemaforoExibicaoDTO;
import br.com.fiap.semaforo.exception.SemaforoNaoEncontradoException;
import br.com.fiap.semaforo.model.Semaforo;
import br.com.fiap.semaforo.repository.SemaforoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SemaforoService {

    @Autowired
    private SemaforoRepository semaforoRepository;

    public SemaforoExibicaoDTO salvar(SemaforoCadastroDTO semaforoCadastroDTO) {
        Semaforo semaforo = new Semaforo();
        BeanUtils.copyProperties(semaforoCadastroDTO, semaforo);
        return new SemaforoExibicaoDTO(semaforoRepository.save(semaforo));
    }

    public Page<SemaforoExibicaoDTO> listarTodos(Pageable paginacao) {
        return semaforoRepository
                .findAll(paginacao)
                .map(SemaforoExibicaoDTO::new);
    }

    public SemaforoExibicaoDTO buscarPorId(Long id) {
        Optional<Semaforo> semaforoOptional = semaforoRepository.findById(id);

        if (semaforoOptional.isPresent()) {
            return new SemaforoExibicaoDTO(semaforoOptional.get());
        } else {
            throw new SemaforoNaoEncontradoException("Semáforo não encontrado!");
        }
    }

    public void excluir(Long id) {
        Optional<Semaforo> semaforoOptional = semaforoRepository.findById(id);

        if (semaforoOptional.isPresent()) {
            semaforoRepository.delete(semaforoOptional.get());
        } else {
            throw new SemaforoNaoEncontradoException("Semáforo não encontrado!");
        }
    }

    public SemaforoExibicaoDTO atualizar(SemaforoAtualizacaoDTO semaforoAtualizacaoDTO) {
        Optional<Semaforo> semaforoOptional = semaforoRepository.findById(semaforoAtualizacaoDTO.id());

        if (semaforoOptional.isPresent()) {
            Semaforo semaforoAtualizar = new Semaforo();
            BeanUtils.copyProperties(semaforoAtualizacaoDTO, semaforoAtualizar);
            return new SemaforoExibicaoDTO(semaforoRepository.save(semaforoAtualizar));
        } else {
            throw new SemaforoNaoEncontradoException("Semáforo não encontrado!");
        }
    }
}
