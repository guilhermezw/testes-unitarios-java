package com.spring.junit5.exception;

public class OpcaoInvalidaException extends RuntimeException {
    public OpcaoInvalidaException(String message) {
        super(message);
    }
}
