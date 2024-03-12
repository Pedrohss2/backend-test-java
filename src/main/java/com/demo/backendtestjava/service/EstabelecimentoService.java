package com.demo.backendtestjava.service;

import com.demo.backendtestjava.dto.EstabelecimentoDTO;
import com.demo.backendtestjava.entities.Estabelecimento;
import com.demo.backendtestjava.repository.EstabelecimentoRepository;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository repository;

    @SneakyThrows(Exception.class)
    @Transactional(readOnly = true)
    public EstabelecimentoDTO findById(Long id) {
        Estabelecimento estabelecimento = repository.findById(id).orElseThrow(() -> new Exception("Recurso não encontrado.."));
        return new EstabelecimentoDTO(estabelecimento);
    }

    @Transactional
    public EstabelecimentoDTO insert(EstabelecimentoDTO dto) {
        Estabelecimento estabelecimento = new Estabelecimento();
        BeanUtils.copyProperties(dto, estabelecimento);

        estabelecimento = repository.save(estabelecimento);

        return new EstabelecimentoDTO(estabelecimento);
    }

}
