package com.spring.login.exception.custom;

public class TokenGeracaoException extends RuntimeException {
    public TokenGeracaoException(String message) {
        super(message);
    }
}
