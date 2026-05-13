package com.spring.junit5.model;

public class CalculadoraModel {

    public double soma(Double a , Double b){
        return  a + b;
    }

    public double subtrair(Double a , Double b){
        return  a - b;
    }

    public double multiplicar(Double a , Double b){
        return a * b;
    }

    public double dividir(Double a , Double b){
        return a / b;
    }

    public double raizQuadrada(Double raiz){
        return Math.sqrt(raiz);
    }
}
