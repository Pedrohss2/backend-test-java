package com.demo.backendtestjava.dto;

import com.demo.backendtestjava.entities.Estabelecimento;
import jakarta.validation.constraints.Size;
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
    private Integer quantidadeDeVagas;


    public EstabelecimentoDTO(Estabelecimento estabelecimento) {
        id = estabelecimento.getId();
        nome = estabelecimento.getNome();
        cnpj = estabelecimento.getCnpj();
        endereco = estabelecimento.getEndereco();
        telefone = estabelecimento.getTelefone();
        quantidadeDeVagas = estabelecimento.getQuantidadeDeVagas();
    }


}
