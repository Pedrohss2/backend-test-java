package com.demo.backendtestjava.service;

import com.demo.backendtestjava.dto.EstabelecimentoDTO;
import com.demo.backendtestjava.dto.VeiculoDTO;
import com.demo.backendtestjava.entities.Estabelecimento;
import com.demo.backendtestjava.repository.EstabelecimentoRepository;
import lombok.SneakyThrows;
import org.hibernate.exception.DataException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;


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
