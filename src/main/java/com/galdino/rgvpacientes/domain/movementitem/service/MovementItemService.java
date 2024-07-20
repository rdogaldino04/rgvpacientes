package com.galdino.rgvpacientes.domain.movementitem.service;

import com.galdino.rgvpacientes.domain.batch.model.Batch;
import com.galdino.rgvpacientes.domain.movementitem.model.MovementItem;
import com.galdino.rgvpacientes.domain.movementitem.repository.MovementItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementItemService {

    private final MovementItemRepository movementItemRepository;

    public MovementItemService(MovementItemRepository movementItemRepository) {
        this.movementItemRepository = movementItemRepository;
    }

    public boolean existsByBatch(Batch batch) {
        return this.movementItemRepository.existsByBatch(batch);
    }

    public List<MovementItem> findByMovementId(Long movementId) {
        return this.movementItemRepository.findByMovementId(movementId);
    }

}
