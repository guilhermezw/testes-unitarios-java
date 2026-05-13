package com.spring.junit5.dto;

public class NumeroDTO {

    private Double numeroA;
    private Double numeroB;

    public NumeroDTO(Double numeroA, Double numeroB) {
        this.numeroA = numeroA;
        this.numeroB = numeroB;
    }

    public Double getNumeroA() {
        return numeroA;
    }

    public void setNumeroA(Double numeroA) {
        this.numeroA = numeroA;
    }

    public Double getNumeroB() {
        return numeroB;
    }

    public void setNumeroB(Double numeroB) {
        this.numeroB = numeroB;
    }
}
