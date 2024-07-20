package com.galdino.rgvpacientes.domain.movement.repository;

import com.galdino.rgvpacientes.domain.movement.dto.MovementDTO;
import com.galdino.rgvpacientes.domain.movement.dto.MovementFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovementRepositoryQuery {

    Page<MovementDTO> getByFilter(MovementFilter movementFilter, Pageable pageable);
}
