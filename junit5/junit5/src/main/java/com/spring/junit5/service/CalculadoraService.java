package com.spring.junit5.service;

import com.spring.junit5.dto.NumeroDTO;
import com.spring.junit5.dto.RaizQuadradaDTO;
import com.spring.junit5.exception.ErroAoCalcular;
import com.spring.junit5.model.CalculadoraModel;
import org.springframework.stereotype.Service;

@Service
public class CalculadoraService {


    public double soma (NumeroDTO dto){
        CalculadoraModel calc = new CalculadoraModel();
        return calc.soma(dto.getNumeroA(), dto.getNumeroB());
    }

    public double subtrair (NumeroDTO dto){
        CalculadoraModel calc = new CalculadoraModel();
        return calc.subtrair(dto.getNumeroA() , dto.getNumeroB());
    }

    public double multiplicar (NumeroDTO dto){
        CalculadoraModel calc = new CalculadoraModel();
        return calc.multiplicar(dto.getNumeroA() , dto.getNumeroB());
    }

    public double dividir (NumeroDTO dto){
        CalculadoraModel calc = new CalculadoraModel();

        if (dto.getNumeroA() == 0 || dto.getNumeroB() == 0){
            throw new ErroAoCalcular("A divisão por zero não possui resultado definido no conjunto dos números reais.");
        }

        return calc.dividir(dto.getNumeroA(), dto.getNumeroB());
    }

    public double raizQuadrada (RaizQuadradaDTO dto) {
        CalculadoraModel calc = new CalculadoraModel();
        return calc.raizQuadrada(dto.getRaiz());
    }
}
