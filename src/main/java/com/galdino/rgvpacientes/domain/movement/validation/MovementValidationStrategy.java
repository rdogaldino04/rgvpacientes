package com.galdino.rgvpacientes.domain.movement.validation;

import com.galdino.rgvpacientes.domain.movement.model.Movement;

public interface MovementValidationStrategy {

    void execute(Movement movement);

}
