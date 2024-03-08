package com.demo.backendtestjava.dto;

import com.demo.backendtestjava.entities.Estabelecimento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EstabelecimentoDTO {

    private Long id;
    private String nome;
    private String cnpj;
    private String endereco;
    private String telefone;
    private Integer quantidadeVagasParaMoto;
    private Integer quantidadeVagasParaCarro;


    public EstabelecimentoDTO(Estabelecimento estabelecimento) {
        nome = estabelecimento.getNome();
        cnpj = estabelecimento.getCnpj();
        endereco = estabelecimento.getEndereco();
        telefone = estabelecimento.getTelefone();
        quantidadeVagasParaMoto = estabelecimento.getQuantidadeVagasParaMoto();
        quantidadeVagasParaCarro = estabelecimento.getQuantidadeVagasParaMoto();
    }


}