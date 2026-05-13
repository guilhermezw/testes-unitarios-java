package com.spring.junit5.dto;

import com.spring.junit5.enums.Operacao;

public class HistoricoOperacaoResponseDTO {

    private Long id;
    private String calculo;
    private Operacao operacao;
    private Double resultado;

    public HistoricoOperacaoResponseDTO(Long id, String calculo, Operacao operacao, Double resultado) {
        this.id = id;
        this.calculo = calculo;
        this.operacao = operacao;
        this.resultado = resultado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalculo() {
        return calculo;
    }

    public void setCalculo(String calculo) {
        this.calculo = calculo;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }
}
