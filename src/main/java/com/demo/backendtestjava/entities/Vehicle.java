package com.demo.backendtestjava.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Vehicle")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String brand;
    private String model;
    private String color;
    private String plate;
    private String type;
    @Column(name = "entry_date")
    private LocalDate entryDate;
    @Column(name = "departure_date")
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