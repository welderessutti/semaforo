package br.com.fiap.semaforo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SemaforoNaoEncontradoException extends RuntimeException {

    public SemaforoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
