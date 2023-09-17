package com.galdino.rgvpacientes.dto.mapper;

import com.galdino.rgvpacientes.dto.*;
import com.galdino.rgvpacientes.dto.material.MaterialMovementItemDTO;
import com.galdino.rgvpacientes.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovementMapper {

    private final PatientMapper patientMapper;

    public MovementMapper(PatientMapper patientMapper) {
        this.patientMapper = patientMapper;
    }

    public Movement toEntity(MovementInput movementInput) {
        Movement movement = getMovement(movementInput);
        movementInput.getItems().forEach(itemInput -> {
            MovementItem item = new MovementItem();
            item.setId(itemInput.getId());
            item.setMovement(movement);
            item.setAmount(itemInput.getAmount());

            Material material = new Material();
            material.setId(itemInput.getMaterial().getId());
            material.setName(itemInput.getMaterial().getName());
            item.setMaterial(material);

            movement.addItem(item);
        });
        return movement;
    }

    private static Movement getMovement(MovementInput movementInput) {
        Movement movement = new Movement();
        if (movementInput.getId() != null) {
            movement.setId(movementInput.getId());
        }

        Sector sector = new Sector();
        sector.setId(movementInput.getSector().getId());
        movement.setSector(sector);

        Patient patient = new Patient();
        patient.setId(movementInput.getPatient().getId());
        patient.setName(movementInput.getPatient().getName());
        patient.setCpf(movementInput.getPatient().getCpf());
        movement.setPatient(patient);

        Company company = new Company();
        company.setId(movementInput.getCompany().getId());
        movement.setCompany(company);

        Stock stock = new Stock();
        stock.setId(movementInput.getStock().getId());
        movement.setStock(stock);

        return movement;
    }

    public MovementDTO toDTO(Movement movement) {
        if (movement == null) {
            return null;
        }

        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setId(movement.getId());

        movementDTO.setPatient(
                PatientMovementDTO.builder()
                        .id(movement.getPatient().getId())
                        .name(movement.getPatient().getName())
                        .cpf(movement.getPatient().getCpf())
                        .build()
        );

        // TODO criar mapper company issues #3
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(movement.getCompany().getId());
        companyDTO.setCnpj(movement.getCompany().getCnpj());
        companyDTO.setName(movement.getCompany().getName());
        movementDTO.setCompany(companyDTO);

        // TODO criar mapper sector issues #3
        SectorDTO sectorDTO = new SectorDTO();
        sectorDTO.setId(movement.getSector().getId());
        sectorDTO.setName(movement.getSector().getName());
        movementDTO.setSector(sectorDTO);

        // TODO criar mapper stock issues #3
        StockDTO stockDTO = new StockDTO();
        stockDTO.setId(movement.getStock().getId());
        stockDTO.setName(movement.getStock().getName());
        movementDTO.setStock(stockDTO);

        // TODO criar mapper movementItem issues #3
        List<MovementItemDTO> itemsDTO = movement.getItems().stream()
                .map(item -> {
                    MovementItemDTO itemDTO = new MovementItemDTO();
                    itemDTO.setId(item.getId());
                    itemDTO.setAmount(item.getAmount());

                    // TODO criar mapper material issues #3
                    MaterialMovementItemDTO materialMovementItemDTO = new MaterialMovementItemDTO();
                    materialMovementItemDTO.setId(item.getMaterial().getId());
                    materialMovementItemDTO.setName(item.getMaterial().getName());

                    itemDTO.setMaterial(materialMovementItemDTO);
                    return itemDTO;
                }).collect(Collectors.toList());
        movementDTO.setItems(itemsDTO);

        return movementDTO;
    }

}
