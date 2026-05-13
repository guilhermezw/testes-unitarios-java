package com.spring.junit5.dto;

import jakarta.validation.constraints.NotNull;

public class NumeroDTO {

    @NotNull(message = "Número A é obrigatório")
    private Double numeroA;
    @NotNull(message = "Número A é obrigatório")
    private Double numeroB;

    public NumeroDTO(Double numeroA, Double numeroB) {
        this.numeroA = numeroA;
        this.numeroB = numeroB;
    }

    public @NotNull(message = "Número A é obrigatório") Double getNumeroA() {
        return numeroA;
    }

    public void setNumeroA(@NotNull(message = "Número A é obrigatório") Double numeroA) {
        this.numeroA = numeroA;
    }

    public @NotNull(message = "Número A é obrigatório") Double getNumeroB() {
        return numeroB;
    }

    public void setNumeroB(@NotNull(message = "Número A é obrigatório") Double numeroB) {
        this.numeroB = numeroB;
    }
}
