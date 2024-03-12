package com.demo.backendtestjava.repository;

import com.demo.backendtestjava.entities.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

}
