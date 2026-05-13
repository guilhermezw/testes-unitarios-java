package com.spring.junit5.dto;

import jakarta.validation.constraints.NotNull;

public class PorcentagemDTO {

    @NotNull(message = "Percentual é obrigatório")
    private Double percentual;

    @NotNull(message = "Valor base é obrigatório")
    private Double valor;

    public PorcentagemDTO(Double percentual, Double valor) {
        this.percentual = percentual;
        this.valor = valor;
    }

    public @NotNull(message = "Percentual é obrigatório") Double getPercentual() {
        return percentual;
    }

    public void setPercentual(@NotNull(message = "Percentual é obrigatório") Double percentual) {
        this.percentual = percentual;
    }

    public @NotNull(message = "Valor base é obrigatório") Double getValor() {
        return valor;
    }

    public void setValor(@NotNull(message = "Valor base é obrigatório") Double valor) {
        this.valor = valor;
    }
}
