package com.demo.backendtestjava.dto;

import com.demo.backendtestjava.entities.Estabelecimento;
import com.demo.backendtestjava.entities.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoDTO {

    private Long id;
    private String marca;
    private String modelo;
    private String cor;
    private String placa;
    private String tipo;
    private LocalDate dataDeEntrada;
    private LocalDate dataDeSaida;
    private EstabelecimentoDTO estabelecimentoId;

    public VeiculoDTO(Veiculo veiculo) {
        id = veiculo.getId();
        marca = veiculo.getMarca();
        modelo = veiculo.getModelo();
        cor = veiculo.getCor();
        placa = veiculo.getPlaca();
        tipo = veiculo.getTipo();
        dataDeEntrada = veiculo.getDataDeEntrada();
        dataDeSaida = veiculo.getDataDeSaida();
        estabelecimentoId = new EstabelecimentoDTO(veiculo.getEstabelecimento());
    }
}
