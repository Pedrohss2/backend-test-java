package com.demo.backendtestjava.repository;

import com.demo.backendtestjava.dto.VeiculoDTO;
import com.demo.backendtestjava.entities.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {

}
