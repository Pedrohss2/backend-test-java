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
    private String address;
    private String phone;
    private int quantityOfVacancies;


    public EstablishmentDTO(Establishment establishment) {
        id = establishment.getId();
        name = establishment.getName();
        cnpj = establishment.getCnpj();
        address = establishment.getAddress();
        phone = establishment.getPhone();
        quantityOfVacancies = establishment.getQuantityOfVacancies();
    }
    



}
