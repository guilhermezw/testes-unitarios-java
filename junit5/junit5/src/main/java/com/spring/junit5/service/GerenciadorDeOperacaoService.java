package com.spring.junit5.service;

import com.spring.junit5.dto.*;
import com.spring.junit5.enums.Operacao;
import com.spring.junit5.enums.TipoPorcentagem;
import com.spring.junit5.mapper.HistoricoOperacaoMapper;
import com.spring.junit5.model.HistoricoOperacaoModel;
import com.spring.junit5.repository.HistoricoOperacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GerenciadorDeOperacaoService {

    private final CalculadoraService calc;
    private final HistoricoOperacaoRepository historicoOperacaoRepository;
    private final HistoricoOperacaoMapper historicoOperacaoMapper;


    public GerenciadorDeOperacaoService(CalculadoraService calc, HistoricoOperacaoRepository historicoOperacaoRepository, HistoricoOperacaoMapper historicoOperacaoMapper) {
        this.calc = calc;
        this.historicoOperacaoRepository = historicoOperacaoRepository;
        this.historicoOperacaoMapper = historicoOperacaoMapper;
    }

    public double calcular(Operacao operacao, NumeroDTO dto) {
        return operacao.executar(calc, dto);
    }

    public double raizQuadrada (RaizQuadradaDTO dto) {
        return calc.raizQuadrada(dto.getRaiz());
    }

    public double potencia (PotenciaDTO dto){
        return calc.potencia(dto.getBase() , dto.getExpoente());
    }

    public double calcularPorcentagem (TipoPorcentagem tipo, PorcentagemDTO dto) {
        return switch (tipo) {
            case DE -> calc.porcentagemDe(dto.getPercentual(), dto.getValor());
            case PERCENTUAL -> calc.percentualQueRepresenta(dto.getPercentual(), dto.getValor());
            case AUMENTAR -> calc.aumentarPorcentagem(dto.getPercentual(), dto.getValor());
            case DIMINUIR -> calc.diminuirPorcentagem(dto.getPercentual(), dto.getValor());
        };
    }

    public List<HistoricoOperacaoResponseDTO> historicoOperacoes () {
        return historicoOperacaoRepository
                .findAll()
                .stream()
                .map(historicoOperacaoMapper::toResponseDto)
                .toList();
    }
}
