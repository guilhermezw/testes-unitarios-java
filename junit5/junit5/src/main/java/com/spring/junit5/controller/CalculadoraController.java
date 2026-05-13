package com.spring.junit5.controller;

import com.spring.junit5.dto.*;
import com.spring.junit5.enums.Operacao;
import com.spring.junit5.enums.TipoPorcentagem;
import com.spring.junit5.service.GerenciadorDeOperacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/calculadora")
public class CalculadoraController {

    private final GerenciadorDeOperacaoService gerenciadorDeOperacaoService;

    public CalculadoraController(GerenciadorDeOperacaoService gerenciadorDeOperacaoService) {
        this.gerenciadorDeOperacaoService = gerenciadorDeOperacaoService;
    }

    @PostMapping("/{operacao}")
    public ResponseEntity<Map<String , Object>> calcular(@PathVariable Operacao operacao, @RequestBody @Valid NumeroDTO dto){
        double resultado = gerenciadorDeOperacaoService.calcular(operacao, dto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("result", resultado));
    }

    @PostMapping("/porcentagem/{tipo}")
    public ResponseEntity<Map<String , Object>> calcularPorcentagem(@PathVariable TipoPorcentagem tipo, @RequestBody @Valid PorcentagemDTO dto){
        double resultado = gerenciadorDeOperacaoService.calcularPorcentagem(tipo, dto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("result", resultado));
    }

    @PostMapping("/raiz-quadrada")
    public ResponseEntity<Map<String , Object>> raiz (@RequestBody @Valid RaizQuadradaDTO dto){
        double resultado = gerenciadorDeOperacaoService.raizQuadrada(dto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("result:" , resultado));
    }

    @PostMapping("/potencia")
    public ResponseEntity<Map<String , Object>> potencia (@RequestBody @Valid PotenciaDTO dto){
        double resultado = gerenciadorDeOperacaoService.potencia(dto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("result:" , resultado));
    }

    @GetMapping("/historicos")
    public ResponseEntity<List<HistoricoOperacaoResponseDTO>> historicoOperacoes (){
        return ResponseEntity.status(HttpStatus.OK).body(gerenciadorDeOperacaoService.historicoOperacoes());
    }

    @DeleteMapping("/deletar-historicos")
    public ResponseEntity<Map<String , Object>> deletarHistoricoOperacoes(){
        gerenciadorDeOperacaoService.deletarHistoricoOperacoes();
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("messege" , "Historico deletado" , "success" , true));
    }



}
