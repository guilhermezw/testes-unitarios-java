package com.spring.teste_unitarios.controller;

import com.spring.teste_unitarios.entity.CarroEntity;
import com.spring.teste_unitarios.exception.EntidadeNaoEncontradaException;
import com.spring.teste_unitarios.service.CarroService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(CarroController.class)
class CarroControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CarroService carroService;


    @Test
    void deveSalvarUmCarro() throws Exception {
        CarroEntity carro = new CarroEntity(1L , "Hatch" , 150 , 2027);

        when(carroService.salvarCarro(Mockito.any())).thenReturn(carro);

        String json = """
                {
                    "modelo": "Hatch",
                    "valorDiaria": 150 ,
                    "ano": 2027
                }
                """;

        ResultActions result = mockMvc.perform(
                post("/carros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        result
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.modelo").value("Hatch"));
    }

    @Test
    void deveObterDetalhesCarro() throws Exception {
        CarroEntity carro = new CarroEntity(1L , "Hatch" , 150 , 2027);

        when(carroService.buscarCarroPorId(Mockito.any())).thenReturn(carro);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/carros/1")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.modelo").value("Hatch"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.valorDiaria").value(150))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ano").value(2027));
    }

    @Test
    void deveRetornarNotFoundAoTentarObterDetalhesCarroInexistente() throws Exception {

        when(carroService.buscarCarroPorId(Mockito.any())).thenThrow(EntidadeNaoEncontradaException.class);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/carros/1")
                ).andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    void deveListarCarros() throws Exception {

        var listaCarros = List.of(
                new CarroEntity(1L , "Hatch" , 150 , 2020),
                new CarroEntity(2L , "Sedan" , 150 , 2021),
                new CarroEntity(3L , "Suv" , 150 , 2022)
        );

        when(carroService.listarCarros()).thenReturn(listaCarros);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/carros")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].modelo").value("Hatch"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].modelo").value("Sedan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].modelo").value("Suv"));
    }

    @Test
    void deveAtualizarUmCarro() throws Exception {
        CarroEntity carro = new CarroEntity(1L , "Suv" , 200 , 2027);

        when(carroService.atualizarCarro(Mockito.any() , Mockito.any())).thenReturn(carro);

        String json = """
                {
                    "modelo": "Suv",
                    "valorDiaria": 200 ,
                    "ano": 2027
                }
                """;

        mockMvc.perform(
                MockMvcRequestBuilders.put("/carros/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void deveRetornarNotFoundAoTentarAtualizarCarroInexistente() throws Exception {

        when(carroService.atualizarCarro(Mockito.any() , Mockito.any()))
                .thenThrow(EntidadeNaoEncontradaException.class);

        String json = """
                {
                    "modelo": "Suv",
                    "valorDiaria": 200 ,
                    "ano": 2027
                }
                """;

        mockMvc.perform(
                MockMvcRequestBuilders.put("/carros/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    void deveDeletarUmCarro() throws Exception {
        Mockito.doNothing().when(carroService).deletarCarro(Mockito.any());

        mockMvc.perform(MockMvcRequestBuilders.delete("/carros/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void deveRetornarNotFoundAoTentarDeletarCarroInexistente() throws Exception {
        Mockito.doThrow(EntidadeNaoEncontradaException.class).when(carroService).deletarCarro(Mockito.any());

        mockMvc.perform(MockMvcRequestBuilders.delete("/carros/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}