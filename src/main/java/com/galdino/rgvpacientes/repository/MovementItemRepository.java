package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.batch.model.Batch;
import com.galdino.rgvpacientes.model.MovementItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovementItemRepository extends JpaRepository<MovementItem, Long> {

    boolean existsByBatch(Batch batch);

    @Query("SELECT i FROM MovementItem i " +
            "JOIN FETCH i.batch b " +
            "JOIN FETCH b.product p " +
            "WHERE i.movement.id = :movementId")
    List<MovementItem> findByMovementId(Long movementId);

}
