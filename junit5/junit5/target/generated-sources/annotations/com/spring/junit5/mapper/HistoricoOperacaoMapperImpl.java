package com.spring.junit5.mapper;

import com.spring.junit5.dto.HistoricoOperacaoResponseDTO;
import com.spring.junit5.enums.Operacao;
import com.spring.junit5.model.HistoricoOperacaoModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-13T16:46:02-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.3 (Microsoft)"
)
@Component
public class HistoricoOperacaoMapperImpl implements HistoricoOperacaoMapper {

    @Override
    public HistoricoOperacaoResponseDTO toResponseDto(HistoricoOperacaoModel historicoOperacao) {
        if ( historicoOperacao == null ) {
            return null;
        }

        Long id = null;
        String calculo = null;
        Operacao operacao = null;
        Double resultado = null;

        id = historicoOperacao.getId();
        calculo = historicoOperacao.getCalculo();
        operacao = historicoOperacao.getOperacao();
        resultado = historicoOperacao.getResultado();

        HistoricoOperacaoResponseDTO historicoOperacaoResponseDTO = new HistoricoOperacaoResponseDTO( id, calculo, operacao, resultado );

        return historicoOperacaoResponseDTO;
    }
}
