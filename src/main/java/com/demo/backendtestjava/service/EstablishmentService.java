package com.demo.backendtestjava.service;

import com.demo.backendtestjava.dto.EstablishmentDTO;
import com.demo.backendtestjava.entities.Establishment;
import com.demo.backendtestjava.entities.Vehicle;
import com.demo.backendtestjava.repository.EstablishmentRepository;
import com.demo.backendtestjava.repository.VehicleRepository;
import com.demo.backendtestjava.service.Exception.DatabaseException;
import com.demo.backendtestjava.service.Exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstablishmentService {

    @Autowired
    private EstablishmentRepository repository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @SneakyThrows(Exception.class)
    @Transactional(readOnly = true)
    public EstablishmentDTO findById(Long id) {
        Establishment estabelecimento = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found."));
        return new EstablishmentDTO(estabelecimento);
    }


    @Transactional(readOnly = true)
    public Page<EstablishmentDTO> findAll(Pageable pageable) {
        Page<Establishment> establishment = repository.findAll(pageable);
        return establishment.map(x -> new EstablishmentDTO(x));
    }


    @Transactional
    public EstablishmentDTO insert(EstablishmentDTO dto) {
        Establishment estabelecimento = new Establishment();
        BeanUtils.copyProperties(dto, estabelecimento);

        estabelecimento = repository.save(estabelecimento);

        return new EstablishmentDTO(estabelecimento);
    }

    @SneakyThrows(Exception.class)
    @Transactional
    public EstablishmentDTO update(Long id, EstablishmentDTO dto) {
        try {
            Establishment estabelecimento = repository.getReferenceById(id);
            estabelecimento.setName(dto.getName());
            estabelecimento.setCnpj(dto.getCnpj());
            estabelecimento.setPhone(dto.getPhone());
            estabelecimento.setAddress(dto.getAdrres());
            estabelecimento.setQuantityOfVacancies(estabelecimento.getQuantityOfVacancies());

            estabelecimento = repository.save(estabelecimento);
            return new EstablishmentDTO(estabelecimento);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource not found.");
        }
    }

    @SneakyThrows(Exception.class)
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteEstablishmentById(Long id) {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found."));

        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("A deleção não pode ser feita");
        }
    }


    public  boolean parkingCrowded(Establishment establishment) {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.size() >= establishment.getQuantityOfVacancies();
    }

    public static void exitOfVehicle(Establishment establishment) {
        int quantity = establishment.getQuantityOfVacancies() - 1;
    }

}
