package com.spring.teste_unitarios.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ClienteTest {

    @Test
    @DisplayName("Deve criar um cliente com nome")
    void deveCriarClienteComNome(){
        // 1. Cenário
        var cliente = new Cliente("Maria");

        // 2. Execução
        String nome = cliente.getNome();

        // 3. Verificação
        Assertions.assertNotNull(nome);
        Assertions.assertTrue(nome.startsWith("M"));
        Assertions.assertFalse(nome.length() == 100);

        assertThat(nome).isEqualTo("Maria");
        assertThat(nome).isLessThan("Maria Fernada");
        assertThat(nome).contains("Ma");

        assertThat(nome.length()).isLessThan(100);
    }

    @Test
    @DisplayName("Deve criar um cliente sem nome")
    void deveCriarClienteSemNome(){
        // 1. Cenário
        var cliente = new Cliente(null);

        // 2. Execução
        var nome = cliente.getNome();

        // 3. Verificação
        Assertions.assertNull(nome);
    }
}
