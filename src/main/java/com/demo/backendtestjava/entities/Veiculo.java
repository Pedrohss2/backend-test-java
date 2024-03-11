package com.demo.backendtestjava.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "veiculo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private String modelo;
    private String cor;
    private String placa;
    private String tipo;
    private LocalDate dataDeEntrada;
    private LocalDate dataDeSaida;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id")
    private Estabelecimento estabelecimento;

}