package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long>, MaterialRepositoryQuery {

}
