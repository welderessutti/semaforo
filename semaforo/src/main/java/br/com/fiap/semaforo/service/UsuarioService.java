package br.com.fiap.semaforo.service;

import br.com.fiap.semaforo.dto.UsuarioAtualizacaoDTO;
import br.com.fiap.semaforo.dto.UsuarioCadastroDTO;
import br.com.fiap.semaforo.dto.UsuarioExibicaoDTO;
import br.com.fiap.semaforo.exception.UsuarioNaoEncontradoException;
import br.com.fiap.semaforo.model.Usuario;
import br.com.fiap.semaforo.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDTO salvar(UsuarioCadastroDTO usuarioCadastroDTO) {
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioCadastroDTO.senha());
        Usuario usuario = new Usuario();

        BeanUtils.copyProperties(usuarioCadastroDTO, usuario);
        usuario.setSenha(senhaCriptografada);

        return new UsuarioExibicaoDTO(usuarioRepository.save(usuario));
    }

    public Page<UsuarioExibicaoDTO> listarTodos(Pageable paginacao) {
        return usuarioRepository
                .findAll(paginacao)
                .map(UsuarioExibicaoDTO::new);
    }

    public UsuarioExibicaoDTO buscarPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            return new UsuarioExibicaoDTO(usuarioOptional.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado!");
        }
    }

    public void excluir(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            usuarioRepository.delete(usuarioOptional.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado!");
        }
    }

    public UsuarioExibicaoDTO atualizar(UsuarioAtualizacaoDTO usuarioAtualizacaoDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioAtualizacaoDTO.id());

        if (usuarioOptional.isPresent()) {
            Usuario usuarioAtualizar = new Usuario();
            BeanUtils.copyProperties(usuarioAtualizacaoDTO, usuarioAtualizar);
            return new UsuarioExibicaoDTO(usuarioRepository.save(usuarioAtualizar));
        } else {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado!");
        }
    }
}
