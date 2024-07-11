package com.galdino.rgvpacientes.service;

import com.galdino.rgvpacientes.dto.batch.BatchIdDTO;
import com.galdino.rgvpacientes.dto.movement.MovementIdDTO;
import com.galdino.rgvpacientes.dto.movement.MovementInput;
import com.galdino.rgvpacientes.dto.movementitem.MovementItemInput;
import com.galdino.rgvpacientes.dto.patient.PatientIdDTO;
import com.galdino.rgvpacientes.dto.stock.StockIdDTO;
import com.galdino.rgvpacientes.enums.MovementType;
import com.galdino.rgvpacientes.mapper.BatchMapper;
import com.galdino.rgvpacientes.mapper.MovementMapper;
import com.galdino.rgvpacientes.model.Movement;
import com.galdino.rgvpacientes.model.Patient;
import com.galdino.rgvpacientes.model.Stock;
import com.galdino.rgvpacientes.repository.BatchRepository;
import com.galdino.rgvpacientes.repository.MovementItemRepository;
import com.galdino.rgvpacientes.repository.MovementRepository;
import com.galdino.rgvpacientes.service.movement.MovementItemService;
import com.galdino.rgvpacientes.service.movement.MovementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validator;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

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


    @BeforeEach
    public void setUp() {
        batchRepository = mock(BatchRepository.class);
        productService = mock(ProductService.class);
        mock(Validator.class);
        batchMapper = mock(BatchMapper.class);
        movementItemRepository = mock(MovementItemRepository.class);

        movementRepository = mock(MovementRepository.class);
        movementItemService = mock(MovementItemService.class);
        movementMapper = mock(MovementMapper.class);
        movementValidationStrategies = mock(List.class);
        movementService = new MovementService(movementRepository, movementItemService, movementMapper, movementValidationStrategies);
    }

    @Test
    void shouldCreateMovement() {
        MovementItemInput movementItemInput = MovementItemInput.builder()
                .id(null)
                .batch(BatchIdDTO.builder().id(1L).build())
                .quantity(BigInteger.valueOf(3))
                .build();
        MovementItemInput movementItemInput2 = MovementItemInput.builder()
                .id(null)
                .batch(BatchIdDTO.builder().id(2L).build())
                .quantity(BigInteger.valueOf(2))
                .build();

        MovementInput movementInput = MovementInput.builder()
                .id(null)
                .movementType(MovementType.INPUT)
                .stock(StockIdDTO.builder().id(1L).build())
                .patient(PatientIdDTO.builder().id(1L).build())
                .items(Arrays.asList(movementItemInput, movementItemInput2))
                .build();

        when(movementMapper.toEntity(any(MovementInput.class))).thenAnswer(invocation -> {
            MovementInput input = invocation.getArgument(0);
            Movement movement = new Movement();
            movement.setMovementType(input.getMovementType());
            Stock stock = new Stock();
            stock.setId(input.getStock().getId());
            movement.setStock(stock);
            Patient patient = new Patient();
            patient.setId(input.getPatient().getId());
            movement.setPatient(patient);

            return movement;
        });

        when(movementRepository.save(any(Movement.class))).thenAnswer(invocation -> {
            Movement movement = invocation.getArgument(0);
            movement.setId(1L);
            return movement;
        });

        MovementIdDTO movementIdDTO = this.movementService.save(movementInput);
        assert (movementIdDTO != null);
        assert (movementIdDTO.getId() != null);
    }

}
