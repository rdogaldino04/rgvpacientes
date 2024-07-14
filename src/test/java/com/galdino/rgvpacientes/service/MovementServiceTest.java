package com.galdino.rgvpacientes.service;

import com.galdino.rgvpacientes.dto.movement.MovementIdDTO;
import com.galdino.rgvpacientes.dto.movement.MovementInput;
import com.galdino.rgvpacientes.enums.MovementType;
import com.galdino.rgvpacientes.mapper.BatchMapper;
import com.galdino.rgvpacientes.mapper.MovementMapper;
import com.galdino.rgvpacientes.model.*;
import com.galdino.rgvpacientes.repository.BatchRepository;
import com.galdino.rgvpacientes.repository.MovementItemRepository;
import com.galdino.rgvpacientes.repository.MovementRepository;
import com.galdino.rgvpacientes.service.movement.MovementItemService;
import com.galdino.rgvpacientes.service.movement.MovementService;
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

    private BatchService batchService;
    private BatchRepository batchRepository;
    private ProductService productService;
    private BatchMapper batchMapper;
    private MovementItemRepository movementItemRepository;
    private MovementRepository movementRepository;

    private MovementMapper movementMapper;
    private MovementItemService movementItemService;
    private List movementValidationStrategies;
    private MovementService movementService;
    private Validator validator;


    @BeforeEach
    public void setUp() {
        batchRepository = mock(BatchRepository.class);
        productService = mock(ProductService.class);
        validator = mock(Validator.class);
        batchMapper = mock(BatchMapper.class);
        movementItemRepository = mock(MovementItemRepository.class);

        movementRepository = mock(MovementRepository.class);
        movementItemService = mock(MovementItemService.class);
        movementMapper = mock(MovementMapper.class);
        movementValidationStrategies = mock(List.class);
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
                .build();

        when(validator.validate(movementSave)).thenAnswer(invocation -> {
            Movement movement = invocation.getArgument(0);
            Set<ConstraintViolation<Movement>> violations = new HashSet<>();
            if (movement.getPatient() == null) {
                ConstraintViolation<Movement> violation = mock(ConstraintViolation.class);
                when(violation.getMessage()).thenReturn("Patient is required");
                violations.add(violation);
            }
            if (movement.getStockSourceLocation() == null) {
                ConstraintViolation<Movement> violation = mock(ConstraintViolation.class);
                when(violation.getMessage()).thenReturn("Stock source location is required");
                violations.add(violation);
            }
            if (movement.getMovementType() == null) {
                ConstraintViolation<Movement> violation = mock(ConstraintViolation.class);
                when(violation.getMessage()).thenReturn("Movement type is required");
                violations.add(violation);
            }
            return violations;
        });

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
            input.getItems().forEach(itemInput -> {
                MovementItem item = new MovementItem();
                item.setId(itemInput.getId());
                item.setQuantity(itemInput.getQuantity());

                Batch batch = new Batch();
                batch.setId(itemInput.getBatch().getId());
                item.setBatch(batch);

                movement.addItem(item);
            });

            return movement;
        });

        when(movementRepository.save(any(Movement.class))).thenAnswer(invocation -> {
            Movement movement = invocation.getArgument(0);
            movement.setId(1L);
            return movement;
        });

        MovementIdDTO movementIdDTO = this.movementService.save(movementSave);
        assertThat(movementIdDTO).isNotNull();
        assertThat(movementIdDTO.getId()).isNotNull();
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

}
