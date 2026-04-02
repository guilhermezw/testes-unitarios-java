package com.spring.teste_unitarios.model;

import com.spring.teste_unitarios.exception.ReservaInvalidaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ReservaTest {

    @Test
    @DisplayName("Deve criar um Reserva")
    void deveCriarReserva(){
        try{
            // 1. Cenário
            Carro carro = new Carro("Sedan" , 100.0);
            Cliente cliente = new Cliente("Leticia");
            Reserva reserva = new Reserva(cliente , carro , 2);

            // 2. Execução
            reserva.verificarNumeroDias(reserva.getDias());
            double total = carro.calcularValorAluguel(reserva.getDias());

            // 3. Verificação
            Assertions.assertEquals(200.0 , total);
            assertThat(reserva.getDias()).isGreaterThan(0);
            Assertions.assertNotNull(cliente.getNome());

        } catch (ReservaInvalidaException erro){
            throw new ReservaInvalidaException(erro.getMessage());
        }

    }
}
