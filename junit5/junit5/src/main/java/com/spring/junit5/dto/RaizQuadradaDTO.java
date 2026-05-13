package com.spring.junit5.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class RaizQuadradaDTO {

    @NotNull(message = "Número é obrigatório")
    @PositiveOrZero(message = "Não pode calcular raiz de número negativo")
    private Double raiz;

    public RaizQuadradaDTO(Double raiz) {
        this.raiz = raiz;
    }

    public @NotNull(message = "Número é obrigatório") @PositiveOrZero(message = "Não pode calcular raiz de número negativo") Double getRaiz() {
        return raiz;
    }

    public void setRaiz(@NotNull(message = "Número é obrigatório") @PositiveOrZero(message = "Não pode calcular raiz de número negativo") Double raiz) {
        this.raiz = raiz;
    }
}
