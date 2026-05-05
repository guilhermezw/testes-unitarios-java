package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    @Test
    void soma() {
        Calculadora calculadora = new Calculadora();
        double soma = calculadora.soma(2 , 3);

        assertEquals(5, soma);
    }

    @Test
    void subtrair() {
        Calculadora calculadora = new Calculadora();
        double subtrair = calculadora.subtrair(5 , 2);

        assertEquals(3 , subtrair);
    }

    @Test
    void multiplicar() {
        Calculadora calculadora = new Calculadora();
        double multiplicar = calculadora.multiplicar(4 , 3);

        assertEquals(12 , multiplicar);
    }

    @Test
    void dividir() {
        Calculadora calculadora = new Calculadora();
        double dividir = calculadora.dividir(10  , 2);

        assertEquals(5 , dividir);
    }

    @Test
    void dividirZero() {
        Calculadora calculadora = new Calculadora();
        double dividirZero = calculadora.dividir(10 ,0);

        assertEquals(0 , dividirZero);
    }

    @Test
    void numeroNegativo() {
        Calculadora calculadora = new Calculadora();
        double numero = calculadora.soma(-2 , 3);

        assertEquals(1 , numero);
    }

    @Test
    void numeroDecimal() {
        Calculadora calculadora = new Calculadora();
        double numero = calculadora.multiplicar(2.5 , 2);

        assertEquals(5.0 , numero);
    }
}