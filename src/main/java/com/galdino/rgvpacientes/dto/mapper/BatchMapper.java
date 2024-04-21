package com.galdino.rgvpacientes.dto.mapper;

import org.springframework.stereotype.Component;

import com.galdino.rgvpacientes.dto.BatchDTO;
import com.galdino.rgvpacientes.dto.BatchInput;
import com.galdino.rgvpacientes.dto.material.MaterialDTO;
import com.galdino.rgvpacientes.model.Batch;
import com.galdino.rgvpacientes.model.Material;

@Component
public class BatchMapper {

    public Batch toEntity(BatchInput batchInput) {
        if (batchInput == null) {
            return null;
        }

        Batch batch = new Batch();
        batch.setBatchNumber(batchInput.getBatchNumber());
        batch.setManufactureDate(batchInput.getManufactureDate());
        batch.setExpiryDate(batchInput.getExpiryDate());

        if (batchInput.getMaterial() == null) {
            return batch;
        }

        Material materialEntity = new Material();
        materialEntity.setId(batchInput.getMaterial().getId());
        batch.setMaterial(materialEntity);
        return batch;
    }

    public BatchDTO toDTO(Batch batch) {
        if (batch == null) {
            return null;
        }
        if (batch.getMaterial() == null) {
            return BatchDTO.builder()
                    .id(batch.getId())
                    .batchNumber(batch.getBatchNumber())
                    .manufactureDate(batch.getManufactureDate())
                    .expiryDate(batch.getExpiryDate())
                    .build();
        }
        return BatchDTO.builder()
                .id(batch.getId())
                .batchNumber(batch.getBatchNumber())
                .manufactureDate(batch.getManufactureDate())
                .expiryDate(batch.getExpiryDate())
                .material(MaterialDTO.builder()
                        .id(batch.getMaterial().getId())
                        .name(batch.getMaterial().getName())
                        .build())
                .build();
    }

}
