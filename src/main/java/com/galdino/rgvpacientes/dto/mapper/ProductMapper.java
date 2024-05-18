package com.galdino.rgvpacientes.dto.mapper;

import com.galdino.rgvpacientes.dto.product.ProductDTO;
import com.galdino.rgvpacientes.dto.product.ProductInput;
import com.galdino.rgvpacientes.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductInput productInput) {
        Product product = new Product();
        product.setName(productInput.getName());
        return product;
    }

    public ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .createdAt(product.getCreatedAt())
                .build();
    }

}
