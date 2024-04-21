package com.galdino.rgvpacientes.service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.stereotype.Service;

import com.galdino.rgvpacientes.dto.BatchDTO;
import com.galdino.rgvpacientes.dto.BatchInput;
import com.galdino.rgvpacientes.dto.mapper.BatchMapper;
import com.galdino.rgvpacientes.model.Batch;
import com.galdino.rgvpacientes.repository.BatchRepository;

@Service
public class BatchService {

    private final BatchRepository batchRepository;
    private final Validator validator;
    private final BatchMapper batchMapper;

    public BatchService(BatchRepository batchRepository, Validator validator, BatchMapper batchMapper) {
        this.batchRepository = batchRepository;
        this.validator = validator;
        this.batchMapper = batchMapper;
    }

    public BatchDTO create(BatchInput batchInput) {
        Set<ConstraintViolation<BatchInput>> violations = this.validator.validate(batchInput);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        Batch batch = this.batchMapper.toEntity(batchInput);
        Batch batchSave = this.batchRepository.save(batch);
        return this.batchMapper.toDTO(batchSave);
    }

}
