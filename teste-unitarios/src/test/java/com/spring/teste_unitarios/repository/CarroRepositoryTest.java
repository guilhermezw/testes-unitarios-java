package com.spring.teste_unitarios.repository;

import com.spring.teste_unitarios.entity.CarroEntity;
import com.spring.teste_unitarios.model.Carro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CarroRepositoryTest {

    @Autowired
    private CarroRepository carroRepository;

    CarroEntity carro;

    @BeforeEach
    void setUp(){
        carro = new CarroEntity();
        carro.setModelo("Volkswagen");
        carro.setValorDiaria(200.0);
        carro.setAno(2020);
    }

    @Test
    void deveSalvarUmCarro(){

        var carro = new CarroEntity();
        carro.setModelo("Sedan");
        carro.setValorDiaria(100.0);
        carro.setAno(2017);
        carroRepository.save(carro);

        assertNotNull(carro.getId());

    }

    @Test
    void deveBuscarCarroPorId(){

        var carroCriado = carroRepository.save(carro);
        Optional<CarroEntity> carroEncontrado = carroRepository.findById(carroCriado.getId());

        assertThat(carroEncontrado).isPresent();
        assertThat(carroEncontrado.get().getModelo()).isEqualTo("Volkswagen");

    }

    @Test
    void deveAtualizarCarro(){
        var carroCriado = carroRepository.save(carro);

        carroCriado.setAno(2024);

        var carroAtualizado = carroRepository.save(carroCriado);

        assertThat(carroAtualizado.getAno()).isEqualTo(2024);
    }

    @Test
    void deveDeletarCarro(){
        var carroCriado = carroRepository.save(carro);

        carroRepository.deleteById(carroCriado.getId());

        Optional<CarroEntity> carroEncontrado = carroRepository.findById(carroCriado.getId());
        assertThat(carroEncontrado).isEmpty();
    }
}