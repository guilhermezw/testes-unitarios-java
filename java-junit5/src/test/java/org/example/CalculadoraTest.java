package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    @Test
    @DisplayName("Deve calcular a soma dos valores de a e b")
    void soma() {
        Calculadora calculadora = new Calculadora();
        double soma = calculadora.soma(2 , 3);

        assertEquals(5, soma);
    }

    @Test
    @DisplayName("Deve calcular a subtração dos valores de a e b")
    void subtrair() {
        Calculadora calculadora = new Calculadora();
        double subtrair = calculadora.subtrair(5 , 2);

        assertEquals(3 , subtrair);
    }

    @Test
    @DisplayName("Deve calcular a multiplicação dos valores de a e b")
    void multiplicar() {
        Calculadora calculadora = new Calculadora();
        double multiplicar = calculadora.multiplicar(4 , 3);

        assertEquals(12 , multiplicar);
    }

    @Test
    @DisplayName("Deve calcular a divisão dos valores de a e b")
    void dividir() {
        Calculadora calculadora = new Calculadora();
        double dividir = calculadora.dividir(10  , 2);

        assertEquals(5 , dividir);
    }

    @Test
    @DisplayName("Deve calcular um divisão por 0")
    void dividirZero() {
        Calculadora calculadora = new Calculadora();
        double dividirZero = calculadora.dividir(10 ,0);

        assertEquals(0 , dividirZero);
    }

    @Test
    @DisplayName("Deve calcular a soma de número negativo e um positivo")
    void numeroNegativo() {
        Calculadora calculadora = new Calculadora();
        double numero = calculadora.soma(-2 , 3);

        assertEquals(1 , numero);
    }

    @Test
    @DisplayName("Deve calcular a multiplicação dos valores de a e b com um número decimal")
    void numeroDecimal() {
        Calculadora calculadora = new Calculadora();
        double numero = calculadora.multiplicar(2.5 , 2);

        assertEquals(5.0 , numero);
    }
}