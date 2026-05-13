package com.spring.junit5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            ErroAoCalcular.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handlerBadRequestException(RuntimeException erro) {
        return ErroResposta.resposta(HttpStatus.BAD_REQUEST , erro.getMessage());
    }
}
