package com.galdino.rgvpacientes.domain.product.repository;

import com.galdino.rgvpacientes.domain.product.product.ProductFilter;
import org.springframework.data.domain.Page;

import com.galdino.rgvpacientes.domain.product.product.ProductDTO;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryQuery {

    Page<ProductDTO> getProductByFilter(ProductFilter productFilter, Pageable pageable);

}
