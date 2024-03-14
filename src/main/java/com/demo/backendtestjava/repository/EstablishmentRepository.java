package com.demo.backendtestjava.repository;

import com.demo.backendtestjava.entities.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {
}