package com.galdino.rgvpacientes.service;

import com.galdino.rgvpacientes.repository.MaterialRepository;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public boolean existsById(Long materialId) {
        return materialRepository.existsById(materialId);
    }
}
