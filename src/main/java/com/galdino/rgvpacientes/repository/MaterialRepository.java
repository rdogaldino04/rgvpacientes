package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaterialRepository extends JpaRepository<Material, Long>, MaterialRepositoryQuery {

    boolean existsByName(String name);

    Optional<Material> findByName(String name);

}
