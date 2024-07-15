package com.galdino.rgvpacientes.mapper;

import com.galdino.rgvpacientes.dto.movement.MovementDTO;
import com.galdino.rgvpacientes.dto.movement.MovementInput;
import com.galdino.rgvpacientes.dto.movementitem.MovementItemDTO;
import com.galdino.rgvpacientes.dto.user.UserDTO;
import com.galdino.rgvpacientes.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MovementMapper {

    private final PatientMapper patientMapper;
    private final StockMapper stockMapper;
    private final MovementItemMapper itemMapper;

    public MovementMapper(PatientMapper patientMapper, StockMapper stockMapper, MovementItemMapper itemMapper) {
        this.patientMapper = patientMapper;
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
            item.setBatch(batch);

            movement.addItem(item);
        });
        movement.setMovementType(movementInput.getMovementType());
        movement.setName(movementInput.getName());
        movement.setObservation(movementInput.getObservation());
        return movement;
    }

    private static Movement getMovement(MovementInput movementInput) {
        Movement movement = new Movement();
        if (movementInput.getId() != null) {
            movement.setId(movementInput.getId());
        }

        Patient patient = new Patient();
        patient.setId(movementInput.getPatient().getId());
        movement.setPatient(patient);

        Stock stock = new Stock();
        stock.setId(movementInput.getStockSourceLocation().getId());
        movement.setStockSourceLocation(stock);

        if (movementInput.getStockDestinationLocation() != null) {
            Stock stockDestination = new Stock();
            stockDestination.setId(movementInput.getStockDestinationLocation().getId());
            movement.setStockDestinationLocation(stockDestination);
        }

        return movement;
    }

    public MovementDTO toDTO(Movement movement) {
        if (movement == null) {
            return null;
        }
        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setId(movement.getId());
        movementDTO.setPatient(patientMapper.toPatientMovementDTO(movement.getPatient()));
        movementDTO.setStock(stockMapper.toDTO(movement.getStockSourceLocation()));
        List<MovementItemDTO> itemsDTO = movement.getItems().stream()
                .map(itemMapper::toDTO)
                .collect(Collectors.toList());
        movementDTO.setItems(itemsDTO);
        movementDTO.setMovementDate(movement.getMovementDate());
        movementDTO.setMovementType(movement.getMovementType());
        movementDTO.setName(movement.getName());
        movementDTO.setRelatedMovement(MovementDTO.builder()
                .id(Optional.ofNullable(movement.getRelatedMovement()).map(Movement::getId).orElse(null))
                .build());
        movementDTO.setUser(UserDTO.builder()
                .id(movement.getUser().getId())
                .username(movement.getUser().getName())
                .build());
        movementDTO.setObservation(movement.getObservation());
        return movementDTO;
    }

    public Movement updateEntity(MovementInput movementInput) {
        Movement movement = new Movement();
        movement.setId(movementInput.getId());
        movement.setPatient(patientMapper.toPatient(movementInput.getPatient().getId()));
        movement.setStockSourceLocation(stockMapper.toStock(movementInput.getStockSourceLocation().getId()));
        movement.setMovementDate(movementInput.getMovementDate());
        movement.removeItems();
        movementInput.getItems().forEach(itemInput -> {
            MovementItem item = new MovementItem();
            item.setId(itemInput.getId());
            item.setQuantity(itemInput.getQuantity());

            Batch batch = new Batch();
            batch.setId(itemInput.getBatch().getId());
            item.setBatch(batch);

            item.setMovementItemDate(itemInput.getMovementItemDate());

            movement.addItem(item);
        });
        movement.setMovementType(movementInput.getMovementType());
        return movement;
    }
}
