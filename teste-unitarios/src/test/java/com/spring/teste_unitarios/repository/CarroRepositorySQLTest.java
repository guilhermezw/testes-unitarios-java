package com.spring.teste_unitarios.repository;

import com.spring.teste_unitarios.entity.CarroEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class CarroRepositorySQLTest {

    @Autowired
    private CarroRepository carroRepository;

    @Test
    @Sql("/sql/carros.sql")
    void deveBuscarCarroPorModelo(){
        List<CarroEntity> lista = carroRepository.findByModelo("Sedan");

        var carro = lista.stream().findFirst().get();

        assertEquals(1 , lista.size());

        assertThat(carro.getModelo()).isEqualTo("Sedan");
        assertThat(carro.getValorDiaria()).isEqualTo(150.0);
    }
}
