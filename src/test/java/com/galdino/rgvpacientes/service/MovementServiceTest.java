package com.galdino.rgvpacientes.service;

import com.galdino.rgvpacientes.mapper.BatchMapper;
import com.galdino.rgvpacientes.mapper.MovementMapper;
import com.galdino.rgvpacientes.repository.BatchRepository;
import com.galdino.rgvpacientes.repository.MovementItemRepository;
import com.galdino.rgvpacientes.repository.MovementRepository;
import com.galdino.rgvpacientes.service.movement.MovementItemService;
import com.galdino.rgvpacientes.service.movement.MovementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validator;
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
    void testCreate() {
        this.movementItemRepository.save(any());
    }

}
