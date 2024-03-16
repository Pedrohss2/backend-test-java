package com.demo.backendtestjava.service;

import com.demo.backendtestjava.dto.VehicleDTO;
import com.demo.backendtestjava.entities.Establishment;
import com.demo.backendtestjava.entities.Vehicle;
import com.demo.backendtestjava.repository.EstablishmentRepository;
import com.demo.backendtestjava.repository.VehicleRepository;
import com.demo.backendtestjava.service.Exception.DatabaseException;
import com.demo.backendtestjava.service.Exception.ResourceNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;
    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Autowired
    private EstablishmentService establishmentService;

    @SneakyThrows(Exception.class)
    @Transactional(readOnly = true)
    public VehicleDTO findById(Long id) {
        Vehicle vehicle = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));

        return new VehicleDTO(vehicle);
    }

    @SneakyThrows(Exception.class)
    @Transactional
    public VehicleDTO insert(VehicleDTO dto) {
        Vehicle vehicle = new Vehicle();
        copyVehicleToVehicleDto(dto, vehicle);

        Establishment establishment = establishmentRepository.getReferenceById(dto.getEstablishmentId().getId());
        vehicle.setEstablishment(establishment);

        if(establishmentService.parkingCrowded(establishment)) {
            throw new Exception("O estacionamento esta lotado!, tente novamente mais tarde..");
        }

        vehicle = repository.save(vehicle);

        return new VehicleDTO(vehicle);
    }

    @SneakyThrows(Exception.class)
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteVehicleById(Long id) {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));

        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("A deleção não pode ser feita");
        }
    }


    public void copyVehicleToVehicleDto(VehicleDTO dto, Vehicle vehicle) {
        vehicle.setBrand(dto.getBrand());
        vehicle.setModel(dto.getModel());
        vehicle.setColor(dto.getColor());
        vehicle.setPlate(dto.getPlate());
        vehicle.setType(dto.getType());
        vehicle.setEntryDate(dto.getEntryDate());
        vehicle.setDepartureDate(dto.getDepartureDate());
    }


}
