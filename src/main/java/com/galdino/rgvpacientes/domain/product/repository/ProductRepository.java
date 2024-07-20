package com.galdino.rgvpacientes.domain.product.repository;

import com.galdino.rgvpacientes.domain.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQuery {

    boolean existsByName(String name);

    Optional<Product> findByName(String name);

}
