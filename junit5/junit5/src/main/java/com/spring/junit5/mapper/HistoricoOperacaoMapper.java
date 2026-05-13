package com.spring.junit5.mapper;

import com.spring.junit5.dto.HistoricoOperacaoResponseDTO;
import com.spring.junit5.model.HistoricoOperacaoModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HistoricoOperacaoMapper {

    HistoricoOperacaoResponseDTO toResponseDto(HistoricoOperacaoModel historicoOperacao);
}
