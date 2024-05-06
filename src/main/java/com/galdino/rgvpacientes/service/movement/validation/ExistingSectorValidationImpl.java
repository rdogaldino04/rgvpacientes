package com.galdino.rgvpacientes.service.movement.validation;

import com.galdino.rgvpacientes.model.Movement;
import com.galdino.rgvpacientes.service.SectorService;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class ExistingSectorValidationImpl implements  MovementValidationStrategy {

    private final SectorService sectorService;

    public ExistingSectorValidationImpl(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @Override
    public void execute(Movement movement) {
        validateSector(movement);
    }

    private void validateSector(Movement movement) {
        if (!this.sectorService.existsById(movement.getSector().getId())) {
            throw new EntityNotFoundException(String.format("There is no sector with id %d", movement.getSector().getId()));
        }
    }

}
