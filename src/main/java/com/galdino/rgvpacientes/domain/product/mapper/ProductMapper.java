package com.galdino.rgvpacientes.domain.product.mapper;

import com.galdino.rgvpacientes.domain.product.model.Product;
import com.galdino.rgvpacientes.domain.product.product.ProductDTO;
import com.galdino.rgvpacientes.domain.product.product.ProductInput;
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
