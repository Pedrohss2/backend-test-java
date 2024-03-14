package com.demo.backendtestjava.service;

import com.demo.backendtestjava.dto.EstabelecimentoDTO;
import com.demo.backendtestjava.entities.Estabelecimento;
import com.demo.backendtestjava.entities.Veiculo;
import com.demo.backendtestjava.repository.EstabelecimentoRepository;
import com.demo.backendtestjava.repository.VeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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


    @Transactional(readOnly = true)
    public Page<EstabelecimentoDTO> findAll(Pageable pageable) {
        Page<Estabelecimento> estabelecimentos = repository.findAll(pageable);
        return estabelecimentos.map(x -> new EstabelecimentoDTO(x));
    }


    @Transactional
    public EstabelecimentoDTO insert(EstabelecimentoDTO dto) {
        Estabelecimento estabelecimento = new Estabelecimento();
        BeanUtils.copyProperties(dto, estabelecimento);

        estabelecimento = repository.save(estabelecimento);

        return new EstabelecimentoDTO(estabelecimento);
    }

    @SneakyThrows(Exception.class)
    @Transactional
    public EstabelecimentoDTO atualizar(Long id, EstabelecimentoDTO dto) {
        try {
            Estabelecimento estabelecimento = repository.getReferenceById(id);
            estabelecimento.setNome(dto.getNome());
            estabelecimento.setCnpj(dto.getCnpj());
            estabelecimento.setTelefone(dto.getTelefone());
            estabelecimento.setEndereco(dto.getEndereco());
            estabelecimento.setQuantidadeDeVagas(estabelecimento.getQuantidadeDeVagas());

            estabelecimento = repository.save(estabelecimento);
            return new EstabelecimentoDTO(estabelecimento);
        }
        catch (EntityNotFoundException e) {
            throw new Exception("Recurso não encontrado");
        }
    }

    @SneakyThrows(Exception.class)
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deletarEstabelecimentoPorId(Long id) {
        repository.findById(id).orElseThrow(() -> new Exception("Recurso não encontrado"));

        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new Exception("A deleção não pode ser feita");
        }
    }


    public  boolean estacionamentoLotado(Estabelecimento estabelecimento) {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        return veiculos.size() >= estabelecimento.getQuantidadeDeVagas();
    }

    public static void saidaDeVeiculo(Estabelecimento estabelecimento) {
        int quantidade = estabelecimento.getQuantidadeDeVagas() - 1;
    }

}
