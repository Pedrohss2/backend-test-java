package com.demo.backendtestjava.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "estabelecimento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String cnpj;
    @Column(unique = true)
    private String address;
    @Column(unique = true)
    private String phone;
    private int quantityOfVacancies;

    @OneToMany(mappedBy = "establishment")
    private Set<Vehicle> vehicles = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Establishment that)) return false;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(cnpj, that.cnpj)) return false;
        if (!Objects.equals(address, that.address)) return false;
        return Objects.equals(vehicles, that.vehicles);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cnpj != null ? cnpj.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (vehicles != null ? vehicles.hashCode() : 0);
        return result;
    }
}