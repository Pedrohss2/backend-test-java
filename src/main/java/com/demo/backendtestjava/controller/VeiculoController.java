package com.demo.backendtestjava.controller;

import com.demo.backendtestjava.dto.VeiculoDTO;
import com.demo.backendtestjava.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<VeiculoDTO> findById(@PathVariable Long id) {
        VeiculoDTO veiculoDTO = service.findById(id);
        return ResponseEntity.ok().body(veiculoDTO);
    }

    @PostMapping
    public ResponseEntity<VeiculoDTO> insert(@RequestBody VeiculoDTO veiculoDTO) {
        veiculoDTO = service.insert(veiculoDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(veiculoDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(veiculoDTO);
    }




}
