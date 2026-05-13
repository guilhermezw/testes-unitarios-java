package com.spring.junit5.model;

import com.spring.junit5.enums.Operacao;
import com.spring.junit5.enums.TipoPorcentagem;
import jakarta.persistence.*;

@Entity
@Table(name = "historico_operacoes")
public class HistoricoOperacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Operacao operacao;

    @Column(nullable = false)
    private String calculo;

    @Column(nullable = false)
    private Double resultado;

    public HistoricoOperacaoModel() {
    }

    public HistoricoOperacaoModel(Long id, Operacao operacao, String calculo, Double resultado) {
        this.id = id;
        this.operacao = operacao;
        this.calculo = calculo;
        this.resultado = resultado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public String getCalculo() {
        return calculo;
    }

    public void setCalculo(String calculo) {
        this.calculo = calculo;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }
}
