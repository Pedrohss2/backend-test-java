package com.demo.backendtestjava.controller;

import com.demo.backendtestjava.dto.EstabelecimentoDTO;
import com.demo.backendtestjava.service.EstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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



    @GetMapping
    public ResponseEntity<Page<EstabelecimentoDTO>> findAll(Pageable pageable) {
        Page<EstabelecimentoDTO> estabelecimentoDTOPage = service.findAll(pageable);
        return ResponseEntity.ok().body(estabelecimentoDTOPage);
    }


    @PostMapping
    public ResponseEntity<EstabelecimentoDTO> insert(@RequestBody EstabelecimentoDTO estabelecimentoDTO) {
        estabelecimentoDTO = service.insert(estabelecimentoDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(estabelecimentoDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EstabelecimentoDTO> atualizar(@PathVariable Long id, @RequestBody EstabelecimentoDTO dto) {
        dto = service.atualizar(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarEstabelecimentoPorId(@PathVariable Long id) {
        service.deletarEstabelecimentoPorId(id);
        return ResponseEntity.noContent().build();
    }

}
