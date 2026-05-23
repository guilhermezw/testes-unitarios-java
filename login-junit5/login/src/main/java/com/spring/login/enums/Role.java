package com.spring.login.enums;

public enum Role {
    ADMIN("Admin"),
    GERENTE("Gerente"),
    CLIENTE("Cliente");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
