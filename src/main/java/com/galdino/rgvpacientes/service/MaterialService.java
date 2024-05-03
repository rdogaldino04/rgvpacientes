package com.galdino.rgvpacientes.service;

import com.galdino.rgvpacientes.dto.mapper.MaterialMapper;
import com.galdino.rgvpacientes.dto.material.MaterialDTO;
import com.galdino.rgvpacientes.dto.material.MaterialFilter;
import com.galdino.rgvpacientes.dto.material.MaterialInput;
import com.galdino.rgvpacientes.dto.wrapper.PageWrapper;
import com.galdino.rgvpacientes.model.Material;
import com.galdino.rgvpacientes.repository.MaterialRepository;
import com.galdino.rgvpacientes.service.exception.BusinessException;
import com.galdino.rgvpacientes.service.exception.EntityInUseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Optional;

@Service
public class MaterialService {

    public static final String THERE_IS_NO_MATERIAL_WITH_CODE = "There is no material with code %d";
    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;
    private final MovementItemService movementItemService;
    @PersistenceContext
    private EntityManager manager;

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
                .orElseThrow(() -> new EntityNotFoundException(String.format(THERE_IS_NO_MATERIAL_WITH_CODE, id)));
        return MaterialDTO.builder()
                .id(material.getId())
                .name(material.getName())
                .expirationDate(material.getExpirationDate())
                .registrationDate(material.getRegistrationDate())
                .build();
    }

    @Transactional
    public MaterialDTO create(MaterialInput materialInput) {
        if (materialRepository.existsByName(materialInput.getName())) {
            throw new BusinessException(
                    String.format("Material with the name %s already exists", materialInput.getName()));
        }
        Material material = materialRepository.save(this.materialMapper.toEntity(materialInput));
        return this.materialMapper.toDTO(material);
    }

    public PageWrapper<MaterialDTO> getMaterialsByFilter(@Valid MaterialFilter materialFilter, Pageable pageable) {
        Page<MaterialDTO> page = this.materialRepository.getMaterialsByFilter(materialFilter, pageable);
        return new PageWrapper<>(page);
    }

    @Transactional
    public MaterialDTO update(Long id, MaterialInput materialInput) {
        return this.materialRepository.findById(id)
                .map(materialFound -> {
                    materialFound.setName(materialInput.getName());
                    materialFound.setExpirationDate(materialInput.getExpirationDate());

                    manager.detach(materialFound);
                    Optional<Material> existingMaterial = materialRepository.findByName(materialInput.getName());
                    if (existingMaterial.isPresent() && !existingMaterial.get().equals(materialFound)) {
                        throw new BusinessException(
                                String.format("Material with the name %s already exists", materialInput.getName()));
                    }

                    return materialMapper.toDTO(materialRepository.save(materialFound));
                }).orElseThrow(() -> new EntityNotFoundException(String.format(THERE_IS_NO_MATERIAL_WITH_CODE, id)));
    }

    @Transactional
    public void delete(Long id) {
        if (this.movementItemService.existsByMaterial(new Material(id))) {
            throw new EntityInUseException(String.format("Material with code %d is in use and cannot be removed.", id));
        }
        materialRepository.delete(
                materialRepository.findById(id)
                        .orElseThrow(
                                () -> new EntityNotFoundException(String.format(THERE_IS_NO_MATERIAL_WITH_CODE, id))));
    }

}
