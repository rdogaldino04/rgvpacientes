package com.galdino.rgvpacientes.service;

import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import com.galdino.rgvpacientes.service.exception.BusinessException;
import org.springframework.stereotype.Service;

import com.galdino.rgvpacientes.dto.BatchDTO;
import com.galdino.rgvpacientes.dto.BatchInput;
import com.galdino.rgvpacientes.dto.mapper.BatchMapper;
import com.galdino.rgvpacientes.model.Batch;
import com.galdino.rgvpacientes.repository.BatchRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BatchService {

    private final BatchRepository batchRepository;
    private final ProductService productService;
    private final Validator validator;
    private final BatchMapper batchMapper;

    public BatchService(BatchRepository batchRepository, Validator validator, BatchMapper batchMapper, ProductService productService) {
        this.batchRepository = batchRepository;
        this.validator = validator;
        this.batchMapper = batchMapper;
        this.productService = productService;
    }

    @Transactional(rollbackFor = Exception.class)
    public BatchDTO create(BatchInput batchInput) {
        Set<ConstraintViolation<BatchInput>> violations = this.validator.validate(batchInput);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        Batch batch = this.batchMapper.toEntity(batchInput);

        this.batchRepository.detach(batch);
        Optional<Batch> batchExists = this.batchRepository.findByBatchNumber(batch.getBatchNumber());
        if (batchExists.isPresent() && !batchExists.get().equals(batch)) {
            throw new BusinessException(String.format("There is already a batch with number %s", batch.getBatchNumber()));
        }

        this.productService.findById(batchInput.getProductId());

        Batch batchSave = this.batchRepository.save(batch);
        return this.batchMapper.toDTO(batchSave);
    }

    public BatchDTO findById(Long id) {
        return this.batchRepository.findById(id)
                .map(this.batchMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no batch with id %d", id)));
    }
}
