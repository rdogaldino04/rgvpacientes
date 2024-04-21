package com.galdino.rgvpacientes.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.galdino.rgvpacientes.dto.BatchDTO;
import com.galdino.rgvpacientes.dto.BatchInput;
import com.galdino.rgvpacientes.dto.mapper.BatchMapper;
import com.galdino.rgvpacientes.model.Batch;
import com.galdino.rgvpacientes.repository.BatchRepository;

class BatchServiceTest {

    private BatchService batchService;
    private BatchRepository batchRepository;
    private Validator validator;
    private BatchMapper batchMapper;

    @BeforeEach
    public void setUp() {
        batchRepository = mock(BatchRepository.class);
        validator = mock(Validator.class);
        batchMapper = mock(BatchMapper.class);
        batchService = new BatchService(batchRepository, validator, batchMapper);
    }

    @Test
    void testCreate() {
        BatchInput batchInput = new BatchInput();
        batchInput.setBatchNumber("123");
        batchInput.setManufactureDate(LocalDate.of(2021, 1, 1));
        batchInput.setExpiryDate(LocalDate.of(2028, 12, 31));

        when(validator.validate(batchInput)).thenReturn(Set.of());

        Batch batch = new Batch();
        batch.setBatchNumber("123");
        batch.setManufactureDate(LocalDate.of(2021, 1, 1));
        batch.setExpiryDate(LocalDate.of(2028, 12, 31));
        when(batchMapper.toEntity(batchInput)).thenReturn(batch);

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

        BatchDTO batchDTO = batchService.create(batchInput);
        assert (batchDTO != null);

        // Verificando se o método save foi chamado no BatchRepository
        verify(batchRepository, times(1)).save(any(Batch.class));
    }

}
