package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.dto.product.ProductFilter;
import org.springframework.data.domain.Page;

import com.galdino.rgvpacientes.dto.product.ProductDTO;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryQuery {

    Page<ProductDTO> getProductByFilter(ProductFilter productFilter, Pageable pageable);

}
