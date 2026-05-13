package com.spring.junit5.repository;

import com.spring.junit5.model.HistoricoOperacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoOperacaoRepository extends JpaRepository<HistoricoOperacaoModel , Long > {
}
