package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.model.Material;
import com.galdino.rgvpacientes.model.MovementItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementItemRepository extends JpaRepository<MovementItem, Long> {

    boolean existsByMaterial(Material material);

}
