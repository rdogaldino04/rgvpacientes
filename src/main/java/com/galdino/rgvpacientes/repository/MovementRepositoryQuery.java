package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.dto.movement.MovementDTO;
import com.galdino.rgvpacientes.dto.movement.MovementFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovementRepositoryQuery {

    Page<MovementDTO> getByFilter(MovementFilter movementFilter, Pageable pageable);
}
