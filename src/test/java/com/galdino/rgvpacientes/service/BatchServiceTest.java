package com.galdino.rgvpacientes.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import javax.validation.Validator;

import com.galdino.rgvpacientes.repository.MovementItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.galdino.rgvpacientes.dto.batch.BatchDTO;
import com.galdino.rgvpacientes.mapper.BatchMapper;
import com.galdino.rgvpacientes.model.Batch;
import com.galdino.rgvpacientes.repository.BatchRepository;

class BatchServiceTest {

    private BatchService batchService;
    private BatchRepository batchRepository;
    private ProductService productService;
    private BatchMapper batchMapper;
    private MovementItemRepository movementItemRepository;

    @BeforeEach
    public void setUp() {
        batchRepository = mock(BatchRepository.class);
        productService = mock(ProductService.class);
        mock(Validator.class);
        batchMapper = mock(BatchMapper.class);
        movementItemRepository = mock(MovementItemRepository.class);
        batchService = new BatchService(batchRepository, productService, movementItemRepository);
    }

    @Test
    void testCreate() {
        Batch batch = new Batch();
        batch.setBatchNumber("123");
        batch.setManufactureDate(LocalDate.of(2021, 1, 1));
        batch.setExpiryDate(LocalDate.of(2028, 12, 31));

        when(batchRepository.save(any(Batch.class))).thenAnswer(invocation -> {
            Batch batchSave = invocation.getArgument(0);
            batchSave.setId(1L);
            batchSave.setBatchNumber("123");
            batchSave.setManufactureDate(LocalDate.of(2021, 1, 1));
            batchSave.setExpiryDate(LocalDate.of(2028, 12, 31));
            return batchSave;
        });

        BatchDTO batchDTOSave = BatchDTO.builder()
                .id(1L)
                .batchNumber("123")
                .manufactureDate(LocalDate.of(2021, 1, 1))
                .expiryDate(LocalDate.of(2028, 12, 31))
                .build();
        when(batchMapper.toDTO(batch)).thenReturn(batchDTOSave);

        Batch result = batchService.create(batch);
        assert (result != null);

        // Verificando se o método save foi chamado no BatchRepository
        verify(batchRepository, times(1)).save(any(Batch.class));
    }

}
