package com.galdino.rgvpacientes.dto.mapper;

import com.galdino.rgvpacientes.dto.MovementDTO;
import com.galdino.rgvpacientes.dto.MovementInput;
import com.galdino.rgvpacientes.dto.MovementItemDTO;
import com.galdino.rgvpacientes.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovementMapper {

    private final PatientMapper patientMapper;
    private final CompanyMapper companyMapper;
    private final SectorMapper sectorMapper;
    private final StockMapper stockMapper;
    private final MovementItemMapper itemMapper;

    public MovementMapper(PatientMapper patientMapper, CompanyMapper companyMapper, SectorMapper sectorMapper, StockMapper stockMapper, MovementItemMapper itemMapper) {
        this.patientMapper = patientMapper;
        this.companyMapper = companyMapper;
        this.sectorMapper = sectorMapper;
        this.stockMapper = stockMapper;
        this.itemMapper = itemMapper;
    }

    public Movement toEntity(MovementInput movementInput) {
        Movement movement = getMovement(movementInput);
        movementInput.getItems().forEach(itemInput -> {
            MovementItem item = new MovementItem();
            item.setId(itemInput.getId());
            item.setQuantity(itemInput.getQuantity());

            Batch batch = new Batch();
            batch.setId(itemInput.getBatch().getId());
            batch.setBatchNumber(itemInput.getBatch().getBatchNumber());
            item.setBatch(batch);

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
        movementDTO.setPatient(patientMapper.toPatientMovementDTO(movement.getPatient()));
        movementDTO.setCompany(companyMapper.toDTO(movement.getCompany()));
        movementDTO.setSector(sectorMapper.toDTO(movement.getSector()));
        movementDTO.setStock(stockMapper.toDTO(movement.getStock()));
        List<MovementItemDTO> itemsDTO = movement.getItems().stream()
                .map(itemMapper::toDTO)
                .collect(Collectors.toList());
        movementDTO.setItems(itemsDTO);
        return movementDTO;
    }

}
