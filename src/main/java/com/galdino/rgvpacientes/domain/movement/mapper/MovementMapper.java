package com.galdino.rgvpacientes.domain.movement.mapper;

import com.galdino.rgvpacientes.domain.batch.model.Batch;
import com.galdino.rgvpacientes.domain.movement.dto.MovementInput;
import com.galdino.rgvpacientes.domain.movementitem.mapper.MovementItemMapper;
import com.galdino.rgvpacientes.domain.patient.model.Patient;
import com.galdino.rgvpacientes.domain.patient.mapper.PatientMapper;
import com.galdino.rgvpacientes.domain.stock.mapper.StockMapper;
import com.galdino.rgvpacientes.domain.movement.dto.MovementDTO;
import com.galdino.rgvpacientes.domain.movementitem.model.MovementItem;
import com.galdino.rgvpacientes.domain.movementitem.dto.MovementItemDTO;
import com.galdino.rgvpacientes.domain.movement.model.Movement;
import com.galdino.rgvpacientes.domain.stock.model.Stock;
import com.galdino.rgvpacientes.domain.user.dto.UserDTO;
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
        movement.setStatus(movementInput.getStatus());
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
        movementDTO.setStockSourceLocation(stockMapper.toDTO(movement.getStockSourceLocation()));
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
