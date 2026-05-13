package com.spring.junit5.controller;

import com.spring.junit5.dto.NumeroDTO;
import com.spring.junit5.dto.RaizQuadradaDTO;
import com.spring.junit5.service.CalculadoraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
public class CalculadoraController {

    private final CalculadoraService calculadoraService;

    public CalculadoraController(CalculadoraService calculadoraService) {
        this.calculadoraService = calculadoraService;
    }

    @PostMapping("/soma")
    public ResponseEntity<Map<String , Object>> soma (@RequestBody NumeroDTO dto){
        double soma = calculadoraService.soma(dto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("result:" , soma));
    }

    @PostMapping("/subtrair")
    public ResponseEntity<Map<String , Object>> subtrair (@RequestBody NumeroDTO dto){
        double subtrair = calculadoraService.subtrair(dto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("result:" , subtrair));
    }

    @PostMapping("/multiplicar")
    public ResponseEntity<Map<String , Object>> multiplicar (@RequestBody NumeroDTO dto){
        double multiplicar = calculadoraService.multiplicar(dto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("result:" , multiplicar));
    }

    @PostMapping("/dividir")
    public ResponseEntity<Map<String , Object>> dividir (@RequestBody NumeroDTO dto){
        double dividir = calculadoraService.dividir(dto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("result:" , dividir));
    }

    @PostMapping("/raiz")
    public ResponseEntity<Map<String , Object>> raiz (@RequestBody RaizQuadradaDTO dto){
        double raiz = calculadoraService.raizQuadrada(dto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("result:" , raiz));
    }

}
