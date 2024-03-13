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
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String cnpj;
    @Column(unique = true)
    private String endereco;
    @Column(unique = true)
    private String telefone;
    private int quantidadeDeVagas;

    @OneToMany(mappedBy = "estabelecimento")
    private Set<Veiculo> veiculos = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estabelecimento that)) return false;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(cnpj, that.cnpj)) return false;
        if (!Objects.equals(endereco, that.endereco)) return false;
        return Objects.equals(veiculos, that.veiculos);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cnpj != null ? cnpj.hashCode() : 0);
        result = 31 * result + (endereco != null ? endereco.hashCode() : 0);
        result = 31 * result + (veiculos != null ? veiculos.hashCode() : 0);
        return result;
    }
}