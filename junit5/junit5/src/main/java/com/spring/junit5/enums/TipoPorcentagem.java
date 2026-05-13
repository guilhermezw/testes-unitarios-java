package com.spring.junit5.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.spring.junit5.exception.OpcaoInvalidaException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

public enum TipoPorcentagem {

    DE("de"),
    PERCENTUAL("percentual"),
    AUMENTAR("aumentar"),
    DIMINUIR("diminuir");

    private final String valor;

    TipoPorcentagem(String valor) {
        this.valor = valor;
    }

    @JsonValue
    public String getValor() {
        return valor;
    }

    @JsonCreator
    public static TipoPorcentagem from(String value) {
        for (TipoPorcentagem tipo : values()) {
            if (tipo.valor.equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        throw new OpcaoInvalidaException("Tipo de porcentagem inválido: " + value);
    }

    @Component
    public class StringToTipoPorcentagemConverter implements Converter<String, TipoPorcentagem> {

        @Override
        public TipoPorcentagem convert(String value) {
            return TipoPorcentagem.from(value);
        }
    }
}
