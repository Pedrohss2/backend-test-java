package com.demo.backendtestjava.dto;

import com.demo.backendtestjava.entities.Establishment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentDTO {

    private Long id;
    private String name;
    private String cnpj;
    private String adrres;
    private String phone;
    private int quantidadeDeVagas;


    public EstablishmentDTO(Establishment estabelecimento) {
        id = estabelecimento.getId();
        name = estabelecimento.getName();
        cnpj = estabelecimento.getCnpj();
        adrres = estabelecimento.getAddress();
        phone = estabelecimento.getPhone();
        quantidadeDeVagas = estabelecimento.getQuantityOfVacancies();
    }
    



}
