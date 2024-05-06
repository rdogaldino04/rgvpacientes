package com.galdino.rgvpacientes.service.movement;

import com.galdino.rgvpacientes.model.Batch;
import com.galdino.rgvpacientes.repository.MovementItemRepository;
import org.springframework.stereotype.Service;

@Service
public class MovementItemService {

    private final MovementItemRepository movementItemRepository;

    public MovementItemService(MovementItemRepository movementItemRepository) {
        this.movementItemRepository = movementItemRepository;
    }

    public boolean existsByBatch(Batch batch) {
        return this.movementItemRepository.existsByBatch(batch);
    }
}
