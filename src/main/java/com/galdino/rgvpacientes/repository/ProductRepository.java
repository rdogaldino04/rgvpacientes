package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQuery {

    boolean existsByName(String name);

    Optional<Product> findByName(String name);

}
