package br.com.fiap.semaforo.advice;

import br.com.fiap.semaforo.exception.SemaforoNaoEncontradoException;
import br.com.fiap.semaforo.exception.UsuarioNaoEncontradoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException error) {
        Map<String, String> errorMap = new HashMap<>();
        List<FieldError> campos = error.getBindingResult().getFieldErrors();

        for (FieldError campo : campos) {
            errorMap.put(campo.getField(), campo.getDefaultMessage());
        }
        return errorMap;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> handleDataIntegrityViolation(DataIntegrityViolationException error) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("erro", "Usuário já cadastrado!");
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public Map<String, String> handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException error) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("erro", "Usuário não encontrado!");
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SemaforoNaoEncontradoException.class)
    public Map<String, String> handleSemaforoNaoEncontrado(SemaforoNaoEncontradoException error) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("erro", "Semáforo não encontrado!");
        return errorMap;
    }
}
