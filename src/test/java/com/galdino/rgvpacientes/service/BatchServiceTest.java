package com.galdino.rgvpacientes.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import javax.validation.Validator;

import com.galdino.rgvpacientes.domain.batch.service.BatchService;
import com.galdino.rgvpacientes.domain.product.model.Product;
import com.galdino.rgvpacientes.domain.movementitem.repository.MovementItemRepository;
import com.galdino.rgvpacientes.domain.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.galdino.rgvpacientes.domain.batch.mapper.BatchMapper;
import com.galdino.rgvpacientes.domain.batch.model.Batch;
import com.galdino.rgvpacientes.domain.batch.repository.BatchRepository;

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
        batch.setProduct(new Product(1L));

        when(productService.findById(any(Long.class))).thenAnswer(invocation -> {
            Long id = invocation.getArgument(0);
            if (id == 1L) {
                return null;
            }
            return new Product();
        });

        when(batchRepository.save(any(Batch.class))).thenAnswer(invocation -> {
            Batch batchSave = invocation.getArgument(0);
            batchSave.setId(1L);
            batchSave.setBatchNumber("123");
            batchSave.setManufactureDate(LocalDate.of(2021, 1, 1));
            batchSave.setExpiryDate(LocalDate.of(2028, 12, 31));
            return batchSave;
        });

        Batch result = batchService.create(batch);
        assert (result != null);
        assert (result.getId() != null);

        // Verificando se o método save foi chamado no BatchRepository
        verify(batchRepository, times(1)).save(any(Batch.class));
    }

}
