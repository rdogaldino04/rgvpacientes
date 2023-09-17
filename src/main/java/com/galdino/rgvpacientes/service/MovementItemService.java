package com.galdino.rgvpacientes.service;

import com.galdino.rgvpacientes.model.Material;
import com.galdino.rgvpacientes.repository.MovementItemRepository;
import org.springframework.stereotype.Service;

@Service
public class MovementItemService {

    private final MovementItemRepository movementItemRepository;

    public MovementItemService(MovementItemRepository movementItemRepository) {
        this.movementItemRepository = movementItemRepository;
    }

    public boolean existsByMaterial(Material material) {
        return this.movementItemRepository.existsByMaterial(material);
    }
}
