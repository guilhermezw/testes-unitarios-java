package com.spring.junit5.exception;

public class ErroAoCalcular extends RuntimeException {
    public ErroAoCalcular(String message) {
        super(message);
    }
}
