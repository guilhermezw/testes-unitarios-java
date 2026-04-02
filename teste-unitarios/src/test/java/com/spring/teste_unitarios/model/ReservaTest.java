package com.spring.teste_unitarios.model;

import com.spring.teste_unitarios.exception.ReservaInvalidaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;


public class ReservaTest {

    Carro carro;
    Cliente cliente;

    @BeforeEach
    void setUp(){
        carro = new Carro("Sedan" , 100.0);
        cliente = new Cliente("Leticia");
    }

    @Test
    @DisplayName("Deve criar um Reserva")
    void deveCriarReserva(){
        try{
            // 1. Cenário

            Reserva reserva = new Reserva(cliente , carro , 2);

            // 2. Execução
            reserva.verificarNumeroDias(reserva.getDias());
            double total = carro.calcularValorAluguel(reserva.getDias());

            // 3. Verificação
            assertThat(reserva).isNotNull();
            Assertions.assertEquals(200.0 , total);
            assertThat(reserva.getDias()).isGreaterThan(0);
            Assertions.assertNotNull(cliente.getNome());

        } catch (ReservaInvalidaException erro){
            throw new ReservaInvalidaException(erro.getMessage());
        }

    }

    @Test
    void deveDarErroAoCriarUmReservaComDiasNegativo(){
        // JUnit
       Assertions.assertThrows(ReservaInvalidaException.class, () -> new Reserva(cliente , carro , 0));
       Assertions.assertDoesNotThrow(() -> new Reserva(cliente , carro , 1));
    }
}
