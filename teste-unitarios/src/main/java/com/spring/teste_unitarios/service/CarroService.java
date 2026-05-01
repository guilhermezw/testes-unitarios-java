package com.spring.teste_unitarios.service;

import com.spring.teste_unitarios.entity.CarroEntity;
import com.spring.teste_unitarios.exception.EntidadeNaoEncontradaException;
import com.spring.teste_unitarios.exception.RegraNegocioException;
import com.spring.teste_unitarios.repository.CarroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    private final CarroRepository carroRepository;

    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    public List<CarroEntity> listarCarros(){
        return carroRepository.findAll();
    }

    public CarroEntity buscarCarroPorId(Long id){
        return carroRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Carro não encontrado"));
    }

    public CarroEntity salvarCarro(CarroEntity carro){

        if(carro.getValorDiaria() <= 0){
            throw new RegraNegocioException("Preço da diária não pode ser negativo");
        }
        return carroRepository.save(carro);
    }

    public CarroEntity atualizarCarro(Long id , CarroEntity carro){
        var carroEncontrado = carroRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Carro não encontrado"));

        carroEncontrado.setAno(carro.getAno());
        carroEncontrado.setModelo(carro.getModelo());
        carroEncontrado.setValorDiaria(carro.getValorDiaria());

        return carroRepository.save(carroEncontrado);

    }

    public void deletarCarro(Long id){
        var carroEncontrado = carroRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Carro não encontrado"));
        carroRepository.delete(carroEncontrado);
    }
}
