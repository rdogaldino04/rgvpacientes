package com.galdino.rgvpacientes.service.movement.validation;

import com.galdino.rgvpacientes.model.Movement;

public interface MovementValidationStrategy {

    void execute(Movement movement);

}
