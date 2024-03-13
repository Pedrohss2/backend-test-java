package com.demo.backendtestjava.service;

import com.demo.backendtestjava.dto.EstabelecimentoDTO;
import com.demo.backendtestjava.entities.Estabelecimento;
import com.demo.backendtestjava.entities.Veiculo;
import com.demo.backendtestjava.repository.EstabelecimentoRepository;
import com.demo.backendtestjava.repository.VeiculoRepository;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository repository;

    @Autowired
    private  VeiculoRepository veiculoRepository;

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

    public  boolean estacionamentoLotado(Estabelecimento estabelecimento) {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        return veiculos.size() >= estabelecimento.getQuantidadeDeVagas();
    }

    public static void saidaDeVeiculo(Estabelecimento estabelecimento) {
        int quantidade = estabelecimento.getQuantidadeDeVagas() - 1;
    }

}
