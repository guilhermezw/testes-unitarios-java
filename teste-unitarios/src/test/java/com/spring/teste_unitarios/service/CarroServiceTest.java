package com.spring.teste_unitarios.service;

import com.spring.teste_unitarios.entity.CarroEntity;
import com.spring.teste_unitarios.exception.EntidadeNaoEncontradaException;
import com.spring.teste_unitarios.exception.RegraNegocioException;
import com.spring.teste_unitarios.repository.CarroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarroServiceTest {

    @InjectMocks
    CarroService carroService;

    @Mock
    CarroRepository carroRepository;


    @Test
    void deveSalvarUmCarro(){
        CarroEntity carro = new CarroEntity();
        carro.setModelo("Hatch");
        carro.setValorDiaria(100.0);
        carro.setAno(2020);

        when(carroRepository.save(Mockito.any())).thenReturn(carro);

        var carroSalvo = carroService.salvarCarro(carro);

        assertNotNull(carroSalvo);
        assertEquals("Hatch", carroSalvo.getModelo());

        Mockito.verify(carroRepository).save(Mockito.any());

    }

    @Test
    void deveDarErroAoTentarSalvarCarroComDiariaNegativa(){
        CarroEntity carro = new CarroEntity();

        carro.setModelo("Hatch");
        carro.setValorDiaria(0);
        carro.setAno(2020);

        var erro = catchThrowable(() -> carroService.salvarCarro(carro));

        assertThat(erro).isInstanceOf(RegraNegocioException.class);

        Mockito.verify(carroRepository , Mockito.never()).save(Mockito.any());
    }

    @Test
    void deveAtualizarUmCarro(){

        var carroExistente = new CarroEntity();

        carroExistente.setModelo("Suv");
        carroExistente.setValorDiaria(80.0);
        carroExistente.setAno(2026);
        when(carroRepository.findById(1L)).thenReturn(Optional.of(carroExistente));

        var carroAtualizado = new CarroEntity();

        carroAtualizado.setModelo("Suv");
        carroAtualizado.setValorDiaria(80.0);
        carroAtualizado.setAno(2026);
        when(carroRepository.save(Mockito.any())).thenReturn(carroAtualizado);

        Long id = 1L;
        var carro = new CarroEntity();

        carro.setModelo("Hatch");
        carro.setValorDiaria(0);
        carro.setAno(2020);

        var resultado = carroService.atualizarCarro(id , carro);

        assertEquals(resultado.getModelo() , "Suv");
        Mockito.verify(carroRepository , Mockito.times(1)).save(Mockito.any());

    }

    @Test
    void deveDarErroAoTentarAtualizarCarroInexistente(){
        Long id = 1L;
        var carro = new CarroEntity();

        carro.setModelo("Hatch");
        carro.setValorDiaria(100);
        carro.setAno(2020);

        when(carroRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        var erro = catchThrowable(() -> carroService.atualizarCarro(id , carro));

        assertThat(erro).isInstanceOf(EntidadeNaoEncontradaException.class);

        Mockito.verify(carroRepository , Mockito.never()).save(Mockito.any());
    }

    @Test
    void deveDeletarUmCarro(){
        Long id = 1L;
        var carro = new CarroEntity();

        carro.setModelo("Hatch");
        carro.setValorDiaria(100);
        carro.setAno(2020);

        when(carroRepository.findById(Mockito.any())).thenReturn(Optional.of(carro));
        carroService.deletarCarro(id);

        Mockito.verify(carroRepository , Mockito.times(1)).delete(carro);
    }

    @Test
    void deveDarErroAoTentarDeletarCarroInexistente(){
        Long id = 1L;

        when(carroRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        var erro = catchThrowable(() -> carroService.deletarCarro(id));

        assertThat(erro).isInstanceOf(EntidadeNaoEncontradaException.class);

        Mockito.verify(carroRepository , Mockito.never()).delete(Mockito.any());
    }

    @Test
    void deveBuscarCarroPorId(){
        Long id = 1L;
        var carro = new CarroEntity();

        carro.setModelo("Hatch");
        carro.setValorDiaria(100);
        carro.setAno(2020);
        when(carroRepository.findById(Mockito.any())).thenReturn(Optional.of(carro));

        var carroEncontrado = carroService.buscarCarroPorId(id);

        assertThat(carroEncontrado.getModelo()).isEqualTo("Hatch");
        assertThat(carroEncontrado.getValorDiaria()).isEqualTo(100);
        assertThat(carroEncontrado.getAno()).isEqualTo(2020);
    }

    @Test
    void deveListarTodos(){
         var carro = new CarroEntity(1L , "Hatch" , 100 , 2020);
         var carro2 = new CarroEntity(2L , "Suv" , 80 , 2026);

         var lista = List.of(carro , carro2);
         when(carroRepository.findAll()).thenReturn(lista);

         List<CarroEntity> resultado = carroService.listarCarros();

         assertThat(resultado).hasSize(2);
         verify(carroRepository , times(1)).findAll();
         verifyNoMoreInteractions(carroRepository);
    }

}