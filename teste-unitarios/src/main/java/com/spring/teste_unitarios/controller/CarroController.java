package com.spring.teste_unitarios.controller;

import com.spring.teste_unitarios.entity.CarroEntity;
import com.spring.teste_unitarios.exception.EntidadeNaoEncontradaException;
import com.spring.teste_unitarios.exception.RegraNegocioException;
import com.spring.teste_unitarios.model.Carro;
import com.spring.teste_unitarios.service.CarroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/carros")
public class CarroController {

    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }


    @PostMapping
    public ResponseEntity<Object> salvarCarro(@RequestBody CarroEntity carro){
        try {
            var carroCriado = carroService.salvarCarro(carro);
            return ResponseEntity.status(HttpStatus.CREATED).body(carroCriado);
        } catch (RegraNegocioException ex) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(Map.of("message", ex.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarCarro(@PathVariable Long id , @RequestBody CarroEntity carro){
        try{
            carroService.atualizarCarro(id, carro);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntidadeNaoEncontradaException ex){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCarro(@PathVariable Long id){
        try{
            carroService.deletarCarro(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntidadeNaoEncontradaException ex){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarCarroPorId(@PathVariable Long id){
        try {
            var carroEncontrado = carroService.buscarCarroPorId(id);
            return ResponseEntity.status(HttpStatus.OK).body(carroEncontrado);
        } catch (EntidadeNaoEncontradaException ex){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }


    @GetMapping
    public ResponseEntity<List<CarroEntity>> listarCarros(){
        return ResponseEntity.status(HttpStatus.OK).body(carroService.listarCarros());
    }
}
