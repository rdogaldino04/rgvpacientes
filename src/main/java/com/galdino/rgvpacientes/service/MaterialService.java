package com.galdino.rgvpacientes.service;

import com.galdino.rgvpacientes.dto.mapper.MaterialMapper;
import com.galdino.rgvpacientes.dto.material.MaterialDTO;
import com.galdino.rgvpacientes.dto.material.MaterialInput;
import com.galdino.rgvpacientes.model.Material;
import com.galdino.rgvpacientes.repository.MaterialRepository;
import com.galdino.rgvpacientes.service.exception.EntityInUseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;
    private final MovementItemService movementItemService;

    public MaterialService(
            MaterialRepository materialRepository,
            MaterialMapper materialMapper,
            MovementItemService movementItemService) {
        this.materialRepository = materialRepository;
        this.materialMapper = materialMapper;
        this.movementItemService = movementItemService;
    }

    public boolean existsById(Long materialId) {
        return materialRepository.existsById(materialId);
    }

    public MaterialDTO findById(Long id) {
        Material material = this.materialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no material with code %d", id)));
        return MaterialDTO.builder()
                .id(material.getId())
                .name(material.getName())
                .expirationDate(material.getExpirationDate())
                .registrationDate(material.getRegistrationDate())
                .build();
    }

    @Transactional
    public MaterialDTO create(MaterialInput materialInput) {
        Material material = materialRepository.save(this.materialMapper.toEntity(materialInput));
        return this.materialMapper.toDTO(material);
    }

    public List<Material> getAll(String name) {
        return this.materialRepository.getAll(name);
    }

    @Transactional
    public MaterialDTO update(Long id, MaterialInput materialInput) {
        return this.materialRepository.findById(id)
                .map(materialFound -> {
                    materialFound.setName(materialInput.getName());
                    materialFound.setExpirationDate(materialInput.getExpirationDate());
                    return materialMapper.toDTO(materialRepository.save(materialFound));
                }).orElseThrow(() -> new EntityNotFoundException(String.format("There is no material with code %d", id)));
    }

    @Transactional
    public void delete(Long id) {
        if (this.movementItemService.existsByMaterial(new Material(id))) {
            throw new EntityInUseException(String.format("Material with code %d is in use and cannot be removed.", id));
        }
        materialRepository.delete(
                materialRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(String.format("There is no material with code %d", id)))
        );
    }

}
