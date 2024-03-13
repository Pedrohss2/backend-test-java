package com.demo.backendtestjava.dto;

import com.demo.backendtestjava.entities.Estabelecimento;
import com.demo.backendtestjava.entities.Veiculo;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoDTO {

    private Long id;
    @NotBlank(message = "Campo 'marca' não pode ser vazio")
    private String marca;
    private String modelo;
    private String cor;
    @NotBlank(message = "Campo 'placa' não pode ser vazio")
    private String placa;
    private String tipo;

    @NotBlank(message = "Campo 'dataDeEntrada' não pode ser vazio")
    @Past(message = "A data de entrada precisa ser no presente, não pode ser no passado")
    private LocalDate dataDeEntrada;

    @NotBlank(message = "Campo 'dataDeSaida' não pode ser vazio")
    @FutureOrPresent(message = "A data de saida precisa ser no presente ou no futuro")
    private LocalDate dataDeSaida;

    @NotBlank(message = "Campo 'estabelecimentoId' não pode ser vazio")
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
