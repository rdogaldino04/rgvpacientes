package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.model.Product;
import com.galdino.rgvpacientes.model.MovementItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementItemRepository extends JpaRepository<MovementItem, Long> {

    boolean existsByProduct(Product product);

}
