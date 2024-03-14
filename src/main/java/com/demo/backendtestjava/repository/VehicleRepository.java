package com.demo.backendtestjava.repository;

import com.demo.backendtestjava.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
