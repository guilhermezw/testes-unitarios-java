package com.spring.junit5.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ErroResposta (int status, String mensagem, LocalDateTime timestamp) {

    public ErroResposta(int status, String mensagem) {
        this(status, mensagem, LocalDateTime.now());
    }

    public static ErroResposta resposta(HttpStatus status, String mensagem) {
        return new ErroResposta(status.value(), mensagem);
    }

}