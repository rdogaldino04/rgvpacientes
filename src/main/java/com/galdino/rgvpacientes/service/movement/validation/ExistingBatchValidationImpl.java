package com.galdino.rgvpacientes.service.movement.validation;

import com.galdino.rgvpacientes.model.Movement;
import com.galdino.rgvpacientes.service.BatchService;
import org.springframework.stereotype.Component;

@Component
public class ExistingBatchValidationImpl implements MovementValidationStrategy {

    private final BatchService batchService;

    public ExistingBatchValidationImpl(BatchService batchService) {
        this.batchService = batchService;
    }

    @Override
    public void execute(Movement movement) {
        validateBatch(movement);
    }

    private void validateBatch(Movement movement) {
        movement.getItems().forEach(item -> {
            if (item.getBatch() != null && item.getBatch().getId() != null) {
                this.batchService.findById(item.getBatch().getId());
            }
        });
    }

}
