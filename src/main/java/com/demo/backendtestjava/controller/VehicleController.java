package com.demo.backendtestjava.controller;

import com.demo.backendtestjava.dto.VehicleDTO;
import com.demo.backendtestjava.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<VehicleDTO> findById(@PathVariable Long id) {
        VehicleDTO vehicleDTO = service.findById(id);
        return ResponseEntity.ok().body(vehicleDTO);
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> insert(@RequestBody VehicleDTO vehicleDTO) {
        vehicleDTO = service.insert(vehicleDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(vehicleDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(vehicleDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteVehicleById(@PathVariable Long id) {
        service.deleteVehicleById(id);

        return ResponseEntity.noContent().build();
    }

}
