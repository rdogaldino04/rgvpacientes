package com.galdino.rgvpacientes.dto.mapper;

import com.galdino.rgvpacientes.model.Product;
import org.springframework.stereotype.Component;

import com.galdino.rgvpacientes.dto.BatchDTO;
import com.galdino.rgvpacientes.dto.BatchInput;
import com.galdino.rgvpacientes.dto.product.ProductDTO;
import com.galdino.rgvpacientes.model.Batch;

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

        if (batchInput.getProduct() == null) {
            return batch;
        }

        Product productEntity = new Product();
        productEntity.setId(batchInput.getProduct().getId());
        batch.setProduct(productEntity);
        return batch;
    }

    public BatchDTO toDTO(Batch batch) {
        if (batch == null) {
            return null;
        }
        if (batch.getProduct() == null) {
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
                .product(ProductDTO.builder()
                        .id(batch.getProduct().getId())
                        .name(batch.getProduct().getName())
                        .build())
                .build();
    }

}
