package com.demo.backendtestjava.service;

import com.demo.backendtestjava.dto.VeiculoDTO;
import com.demo.backendtestjava.entities.Estabelecimento;
import com.demo.backendtestjava.entities.Veiculo;
import com.demo.backendtestjava.repository.EstabelecimentoRepository;
import com.demo.backendtestjava.repository.VeiculoRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class VeiculoService {

    @Autowired
    private  VeiculoRepository repository;
    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

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
        copiarVeiculoParaVeiculoDto(dto, veiculo);

        Estabelecimento estabelecimento = estabelecimentoRepository.getReferenceById(dto.getEstabelecimentoId().getId());
        veiculo.setEstabelecimento(estabelecimento);

        if(estabelecimentoService.estacionamentoLotado(estabelecimento)) {
            throw new Exception("O estaccionamento esta lotado!, tente novamente mais tarde..");
        }

        veiculo = repository.save(veiculo);

        return new VeiculoDTO(veiculo);
    }

    @SneakyThrows(Exception.class)
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deletaVeiculoPorId(Long id) {
        repository.findById(id).orElseThrow(() -> new Exception("Recurso não encontrado"));

        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new Exception("A deleção não pode ser feita");
        }
    }


    public void copiarVeiculoParaVeiculoDto(VeiculoDTO dto, Veiculo veiculo) {
        veiculo.setMarca(dto.getMarca());
        veiculo.setModelo(dto.getModelo());
        veiculo.setCor(dto.getCor());
        veiculo.setPlaca(dto.getPlaca());
        veiculo.setTipo(dto.getTipo());
        veiculo.setDataDeEntrada(dto.getDataDeEntrada());
        veiculo.setDataDeSaida(dto.getDataDeSaida());
    }


}
