package com.demo.backendtestjava.controller;

import com.demo.backendtestjava.dto.EstabelecimentoDTO;
import com.demo.backendtestjava.service.EstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.GroovyWebApplicationContext;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/estabelecimento")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<EstabelecimentoDTO> findById(@PathVariable Long id) {
        EstabelecimentoDTO estabelecimentoDTO = service.findById(id);

        return ResponseEntity.ok().body(estabelecimentoDTO);
    }


    @PostMapping
    public ResponseEntity<EstabelecimentoDTO> inser(@RequestBody EstabelecimentoDTO estabelecimentoDTO) {
        estabelecimentoDTO = service.insert(estabelecimentoDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(estabelecimentoDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

}
