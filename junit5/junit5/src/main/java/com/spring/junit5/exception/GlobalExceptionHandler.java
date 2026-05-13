package com.spring.junit5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handlerMethodArgumentNotValidException(MethodArgumentNotValidException erro) {
        List<ErroCampo> erros = erro.getFieldErrors()
                .stream()
                .map(e -> new ErroCampo(e.getField(), e.getDefaultMessage()))
                .toList();

        return new ErroResposta(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação nos campos informados.",
                erros
        );

    }

    @ExceptionHandler(CampoInvalidoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handlerCampoInvalidoException(CampoInvalidoException erro) {
        return new ErroResposta(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validação falhou para o campo informado.",
                List.of(new ErroCampo(erro.getCampo(), erro.getMessage()))
        );
    }


    @ExceptionHandler({
            ErroAoCalcularException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handlerBadRequestException(RuntimeException erro) {
        return ErroResposta.resposta(HttpStatus.BAD_REQUEST , erro.getMessage());
    }
}
