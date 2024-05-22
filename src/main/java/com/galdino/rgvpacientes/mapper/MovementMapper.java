package com.galdino.rgvpacientes.mapper;

import com.galdino.rgvpacientes.dto.movement.MovementDTO;
import com.galdino.rgvpacientes.dto.movement.MovementInput;
import com.galdino.rgvpacientes.dto.movementitem.MovementItemDTO;
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
            batch.setId(itemInput.getBatchId());
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
        sector.setId(movementInput.getSectorId());
        movement.setSector(sector);

        Patient patient = new Patient();
        patient.setId(movementInput.getPatientId());
        movement.setPatient(patient);

        Company company = new Company();
        company.setId(movementInput.getCompanyId());
        movement.setCompany(company);

        Stock stock = new Stock();
        stock.setId(movementInput.getStockId());
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
