package com.galdino.rgvpacientes.service;

import com.galdino.rgvpacientes.model.Product;
import com.galdino.rgvpacientes.repository.MovementItemRepository;
import org.springframework.stereotype.Service;

@Service
public class MovementItemService {

    private final MovementItemRepository movementItemRepository;

    public MovementItemService(MovementItemRepository movementItemRepository) {
        this.movementItemRepository = movementItemRepository;
    }

    public boolean existsByProduct(Product product) {
        return this.movementItemRepository.existsByProduct(product);
    }
}
