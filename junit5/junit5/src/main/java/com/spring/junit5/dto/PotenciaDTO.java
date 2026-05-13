package com.spring.junit5.dto;

import jakarta.validation.constraints.NotNull;

public class PotenciaDTO {

    @NotNull(message = "Base é obrigatória")
    private Double base;

    @NotNull(message = "Expoente é obrigatório")
    private Double expoente;

    public PotenciaDTO(Double base, Double expoente) {
        this.base = base;
        this.expoente = expoente;
    }

    public @NotNull(message = "Base é obrigatória") Double getBase() {
        return base;
    }

    public void setBase(@NotNull(message = "Base é obrigatória") Double base) {
        this.base = base;
    }

    public @NotNull(message = "Expoente é obrigatório") Double getExpoente() {
        return expoente;
    }

    public void setExpoente(@NotNull(message = "Expoente é obrigatório") Double expoente) {
        this.expoente = expoente;
    }
}
