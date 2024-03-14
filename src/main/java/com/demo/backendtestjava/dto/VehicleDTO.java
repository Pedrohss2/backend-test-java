package com.demo.backendtestjava.dto;

import com.demo.backendtestjava.entities.Vehicle;
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
public class VehicleDTO {

    private Long id;
    @NotBlank(message = "Campo 'marca' não pode ser vazio")
    private String brand;
    private String model;
    private String color;
    @NotBlank(message = "Campo 'placa' não pode ser vazio")
    private String plate;
    private String type;

    @NotBlank(message = "Campo 'dataDeEntrada' não pode ser vazio")
    @Past(message = "A data de entrada precisa ser no presente, não pode ser no passado")
    private LocalDate entryDate;

    @NotBlank(message = "Campo 'dataDeSaida' não pode ser vazio")
    @FutureOrPresent(message = "A data de saida precisa ser no presente ou no futuro")
    private LocalDate departureDate;

    @NotBlank(message = "Campo 'estabelecimentoId' não pode ser vazio")
    private EstablishmentDTO establishmentId;

    public VehicleDTO(Vehicle vehicle) {
        id = vehicle.getId();
        brand = vehicle.getBrand();
        model = vehicle.getModel();
        color = vehicle.getColor();
        plate = vehicle.getPlate();
        type = vehicle.getType();
        entryDate = vehicle.getEntryDate();
        departureDate = vehicle.getDepartureDate();
        establishmentId = new EstablishmentDTO(vehicle.getEstablishment());
    }
}
