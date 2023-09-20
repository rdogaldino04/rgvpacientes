package com.galdino.rgvpacientes.dto.mapper;

import com.galdino.rgvpacientes.dto.material.MaterialDTO;
import com.galdino.rgvpacientes.dto.material.MaterialInput;
import com.galdino.rgvpacientes.dto.material.MaterialMovementItemDTO;
import com.galdino.rgvpacientes.model.Material;
import org.springframework.stereotype.Component;

@Component
public class MaterialMapper {

    public Material toEntity(MaterialInput materialInput) {
        Material material = new Material();
        material.setName(materialInput.getName());
        material.setExpirationDate(materialInput.getExpirationDate());
        return material;
    }

    public MaterialDTO toDTO(Material material) {
        return MaterialDTO.builder()
                .id(material.getId())
                .name(material.getName())
                .expirationDate(material.getExpirationDate())
                .registrationDate(material.getRegistrationDate())
                .build();
    }

    public MaterialMovementItemDTO toMaterialMovementItemDTO(Material material) {
        if (material == null) {
            return null;
        }
        MaterialMovementItemDTO materialMovementItemDTO = new MaterialMovementItemDTO();
        materialMovementItemDTO.setId(material.getId());
        materialMovementItemDTO.setName(material.getName());
        return materialMovementItemDTO;
    }

}
