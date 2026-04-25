package com.spring.teste_unitarios.repository;

import com.spring.teste_unitarios.entity.CarroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<CarroEntity, Long> {

    List<CarroEntity> findByModelo(String modelo);
}
