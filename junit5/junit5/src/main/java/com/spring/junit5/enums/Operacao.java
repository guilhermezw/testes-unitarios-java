package com.spring.junit5.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.spring.junit5.dto.NumeroDTO;
import com.spring.junit5.exception.OpcaoInvalidaException;
import com.spring.junit5.service.CalculadoraService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

public enum Operacao {

    SOMAR("somar") {
        @Override
        public double executar(CalculadoraService calc, NumeroDTO dto) {
            return calc.somar(dto.getNumeroA(), dto.getNumeroB());
        }
    },

    SUBTRACAO("subtracao") {
        @Override
        public double executar(CalculadoraService calc, NumeroDTO dto) {
            return calc.subtrair(dto.getNumeroA(), dto.getNumeroB());
        }
    },

    MULTIPLICACAO("multiplicacao") {
        @Override
        public double executar(CalculadoraService calc, NumeroDTO dto) {
            return calc.multiplicar(dto.getNumeroA(), dto.getNumeroB());
        }
    },

    DIVISAO("divisao") {
        @Override
        public double executar(CalculadoraService calc, NumeroDTO dto) {
            return calc.dividir(dto.getNumeroA(), dto.getNumeroB());
        }
    },

    PORCENTAGEM("porcentagem"),
    RAIZ_QUADRADA("raiz quadrada"),
    POTENCIA("potencia");

    private final String value;

    Operacao(String value) {
        this.value = value;
    }

    public double executar(CalculadoraService calc, NumeroDTO dto){
        throw new OpcaoInvalidaException(
                "Essa operação não e suportada nesse método."
        );
    }

    @JsonCreator
    public static Operacao from(String value) {
        for (Operacao op : values()) {
            if (op.value.equalsIgnoreCase(value)) {
                return op;
            }
        }
        throw new OpcaoInvalidaException("Operação inválida: " + value);
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Component
    public static class StringOperacaoConverter implements Converter<String, Operacao> {

        @Override
        public Operacao convert(String value) {
            return Operacao.from(value);
        }
    }
}