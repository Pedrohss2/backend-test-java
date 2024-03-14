package com.demo.backendtestjava.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "veiculo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private String color;
    @Column(unique = true)
    private String plate;
    private String type;
    private LocalDate entryDate;
    private LocalDate departureDate;

    @ManyToOne
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;

        if (!Objects.equals(id, vehicle.id)) return false;
        return Objects.equals(plate, vehicle.plate);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (plate != null ? plate.hashCode() : 0);
        return result;
    }
}