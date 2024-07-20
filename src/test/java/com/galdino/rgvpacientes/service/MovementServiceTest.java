package com.galdino.rgvpacientes.service;

import com.galdino.rgvpacientes.domain.batch.model.Batch;
import com.galdino.rgvpacientes.domain.movement.dto.MovementIdDTO;
import com.galdino.rgvpacientes.domain.movement.dto.MovementInput;
import com.galdino.rgvpacientes.domain.movement.enums.MovementName;
import com.galdino.rgvpacientes.domain.movement.enums.MovementType;
import com.galdino.rgvpacientes.domain.movement.mapper.MovementMapper;
import com.galdino.rgvpacientes.domain.movement.model.Movement;
import com.galdino.rgvpacientes.domain.movement.repository.MovementRepository;
import com.galdino.rgvpacientes.domain.movement.service.MovementService;
import com.galdino.rgvpacientes.domain.movementitem.model.MovementItem;
import com.galdino.rgvpacientes.domain.movementitem.service.MovementItemService;
import com.galdino.rgvpacientes.domain.patient.model.Patient;
import com.galdino.rgvpacientes.domain.stock.model.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MovementServiceTest {

    private MovementRepository movementRepository;
    private MovementMapper movementMapper;
    private MovementService movementService;
    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = mock(Validator.class);
        movementRepository = mock(MovementRepository.class);
        MovementItemService movementItemService = mock(MovementItemService.class);
        movementMapper = mock(MovementMapper.class);
        List movementValidationStrategies = mock(List.class);
        movementService = new MovementService(movementRepository, movementItemService, movementMapper, movementValidationStrategies, validator);
    }

    @Test
    void shouldCreateMovement() {
        MovementItem movementItemSave = MovementItem.builder()
                .id(null)
                .batch(Batch.builder().id(1L).build())
                .quantity(BigInteger.valueOf(3))
                .build();
        MovementItem movementItemSave2 = MovementItem.builder()
                .id(null)
                .batch(Batch.builder().id(2L).build())
                .quantity(BigInteger.valueOf(2))
                .build();

        Movement movementSave = Movement.builder()
                .id(null)
                .movementType(MovementType.INPUT)
                .stockSourceLocation(Stock.builder().id(1L).build())
                .patient(Patient.builder().id(1L).build())
                .items(Arrays.asList(movementItemSave, movementItemSave2))
                .name(MovementName.ENTRADA_AVULSA)
                .build();

        mockValidatorBehavior(movementSave);
        mockMovementRepositorySave();

        Movement save = this.movementService.save(movementSave);
        assertThat(save).isNotNull();
        assertThat(save.getId()).isNotNull();
    }

    @Test
    void shouldUpdateMovement() {
        MovementItem movementItemSave = MovementItem.builder()
                .id(1L)
                .batch(Batch.builder().id(1L).build())
                .quantity(BigInteger.valueOf(3))
                .build();
        MovementItem movementItemSave2 = MovementItem.builder()
                .id(null)
                .batch(Batch.builder().id(2L).build())
                .quantity(BigInteger.valueOf(2))
                .build();
        MovementItem movementItemSave3 = MovementItem.builder()
                .id(null)
                .batch(Batch.builder().id(3L).build())
                .quantity(BigInteger.valueOf(2))
                .build();

        Movement movementSave = Movement.builder()
                .id(1L)
                .movementType(MovementType.INPUT)
                .stockSourceLocation(Stock.builder().id(1L).build())
                .patient(Patient.builder().id(1L).build())
                .items(Arrays.asList(movementItemSave, movementItemSave2, movementItemSave3))
                .build();

        when(movementRepository.existsById(1L)).thenReturn(true);

        when(movementMapper.updateEntity(any(MovementInput.class))).thenAnswer(invocation -> {
            MovementInput input = invocation.getArgument(0);
            Movement movement = new Movement();
            movement.setId(input.getId());
            movement.setMovementType(input.getMovementType());
            Stock stock = new Stock();
            stock.setId(input.getStockSourceLocation().getId());
            movement.setStockSourceLocation(stock);
            Patient patient = new Patient();
            patient.setId(input.getPatient().getId());
            movement.setPatient(patient);

            input.getItems().forEach(itemInput -> {
                MovementItem item = new MovementItem();
                Long id = (itemInput.getId() == null) ? (long) (Math.random() * 1000) : itemInput.getId();
                item.setId(id);
                item.setQuantity(itemInput.getQuantity());

                Batch batch = new Batch();
                batch.setId(itemInput.getBatch().getId());
                item.setBatch(batch);

                movement.addItem(item);
            });

            return movement;
        });

        when(movementRepository.save(any(Movement.class))).thenAnswer(invocation -> invocation.getArgument(0));

        MovementIdDTO movementIdDTO = this.movementService.update(1L, movementSave);
        assertThat(movementIdDTO).isNotNull();
        assertThat(movementIdDTO.getId()).isNotNull();
        verify(movementRepository).save(any(Movement.class));
    }

    @Test
    void shouldNotCreateMovementWithNoItems() {
        Movement movementSave = Movement.builder()
                .id(null)
                .movementType(MovementType.INPUT)
                .stockSourceLocation(Stock.builder().id(1L).build())
                .patient(Patient.builder().id(1L).build())
                .items(null)
                .build();

        when(movementMapper.toEntity(any(MovementInput.class))).thenAnswer(invocation -> {
            MovementInput input = invocation.getArgument(0);
            Movement movement = new Movement();
            movement.setMovementType(input.getMovementType());
            Stock stock = new Stock();
            stock.setId(input.getStockSourceLocation().getId());
            movement.setStockSourceLocation(stock);
            Patient patient = new Patient();
            patient.setId(input.getPatient().getId());
            movement.setPatient(patient);

            return movement;
        });

        try {
            this.movementService.save(movementSave);
            assertThat(false).isTrue();
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("The movement must have at least one item");
        }
    }

    private void mockMovementRepositorySave() {
        when(movementRepository.save(any(Movement.class))).thenAnswer(invocation -> {
            Movement movement = invocation.getArgument(0);
            movement.setId(1L);
            return movement;
        });
    }

    private void mockValidatorBehavior(Movement movementSave) {
        when(validator.validate(movementSave)).thenAnswer(invocation -> {
            Movement movement = invocation.getArgument(0);
            Set<ConstraintViolation<Movement>> violations = new HashSet<>();
            checkAndAddViolation(movement.getPatient() == null, violations, "Patient is required");
            checkAndAddViolation(movement.getStockSourceLocation() == null, violations, "Stock source location is required");
            checkAndAddViolation(movement.getMovementType() == null, violations, "Movement type is required");
            return violations;
        });
    }

    private void checkAndAddViolation(boolean condition, Set<ConstraintViolation<Movement>> violations, String message) {
        if (condition) {
            ConstraintViolation<Movement> violation = mock(ConstraintViolation.class);
            when(violation.getMessage()).thenReturn(message);
            violations.add(violation);
        }
    }
}
