package com.spring.teste_unitarios.repository;

import com.spring.teste_unitarios.entity.CarroEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CarroRepositoryTest {

    @Autowired
    private CarroRepository carroRepository;

    @Test
    void deveSalvarUmCarro(){

        var carro = new CarroEntity();
        carro.setModelo("Sedan");
        carro.setValorDia(100.0);
        carroRepository.save(carro);

        assertNotNull(carro.getId());

    }
}