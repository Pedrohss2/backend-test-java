package com.demo.backendtestjava.controller;

import com.demo.backendtestjava.dto.EstablishmentDTO;
import com.demo.backendtestjava.service.EstablishmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/establishment")
public class EstablishmentController {

    @Autowired
    private EstablishmentService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<EstablishmentDTO> findById(@PathVariable Long id) {
        EstablishmentDTO establishmentDTO = service.findById(id);

        return ResponseEntity.ok().body(establishmentDTO);
    }


    @GetMapping
    public ResponseEntity<Page<EstablishmentDTO>> findAll(Pageable pageable) {
        Page<EstablishmentDTO> estabelecimentoDTOPage = service.findAll(pageable);
        return ResponseEntity.ok().body(estabelecimentoDTOPage);
    }


    @PostMapping
    public ResponseEntity<EstablishmentDTO> insert(@Valid @RequestBody EstablishmentDTO establishmentDTO) {
        establishmentDTO = service.insert(establishmentDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(establishmentDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EstablishmentDTO> update(@PathVariable Long id, @Valid @RequestBody EstablishmentDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteEstablishmentById(@PathVariable Long id) {
        service.deleteEstablishmentById(id);
        return ResponseEntity.noContent().build();
    }

}
