package com.demo.backendtestjava.repository;

import com.demo.backendtestjava.entities.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {
}
