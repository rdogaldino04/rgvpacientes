package com.galdino.rgvpacientes.domain.movement.repository;

import com.galdino.rgvpacientes.domain.movement.dto.MovementFilter;
import com.galdino.rgvpacientes.domain.movement.model.Movement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovementRepositoryQuery {

    Page<Movement> getByFilter(MovementFilter movementFilter, Pageable pageable);
}
