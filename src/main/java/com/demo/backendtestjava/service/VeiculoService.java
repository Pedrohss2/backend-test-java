package com.demo.backendtestjava.service;

import com.demo.backendtestjava.dto.EstabelecimentoDTO;
import com.demo.backendtestjava.dto.VeiculoDTO;
import com.demo.backendtestjava.entities.Estabelecimento;
import com.demo.backendtestjava.entities.Veiculo;
import com.demo.backendtestjava.repository.EstabelecimentoRepository;
import com.demo.backendtestjava.repository.VeiculoRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private  VeiculoRepository repository;
    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @SneakyThrows(Exception.class)
    @Transactional(readOnly = true)
    public VeiculoDTO findById(Long id) {
        Veiculo veiculo = repository.findById(id).orElseThrow(() -> new Exception("Recurso não encontrado"));

        return new VeiculoDTO(veiculo);
    }

    @SneakyThrows(Exception.class)
    @Transactional
    public VeiculoDTO insert(VeiculoDTO dto) {
        Veiculo veiculo = new Veiculo();
        copyToDto(dto, veiculo);

        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(dto.getEstabelecimentoId().getId());
        veiculo.setEstabelecimento(estabelecimento);

        if(estacionamentoLotado(5)) {
            throw new Exception("Quantidade de vagas atingida, sorry");
        }
        veiculo = repository.save(veiculo);

        return new VeiculoDTO(veiculo);
    }

    public boolean estacionamentoLotado(Integer max) {
        List<Veiculo> veiculos = repository.findAll();

        return  veiculos.size() >= max;
    }

    public void copyToDto(VeiculoDTO dto, Veiculo veiculo) {
        veiculo.setMarca(dto.getMarca());
        veiculo.setModelo(dto.getModelo());
        veiculo.setCor(dto.getCor());
        veiculo.setPlaca(dto.getPlaca());
        veiculo.setTipo(dto.getTipo());
    }

}
