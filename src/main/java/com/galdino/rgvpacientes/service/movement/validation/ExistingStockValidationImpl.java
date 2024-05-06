package com.galdino.rgvpacientes.service.movement.validation;

import com.galdino.rgvpacientes.model.Movement;
import com.galdino.rgvpacientes.service.StockService;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class ExistingStockValidationImpl implements  MovementValidationStrategy {

    private final StockService stockService;

    public ExistingStockValidationImpl(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public void execute(Movement movement) {
        validateStock(movement);
    }

    private void validateStock(Movement movement) {
        if (!this.stockService.existsById(movement.getStock().getId())) {
            throw new EntityNotFoundException(String.format("There is no stock with id %d", movement.getStock().getId()));
        }
    }

}
