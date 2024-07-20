package com.galdino.rgvpacientes.domain.movement.repository;

import com.galdino.rgvpacientes.domain.movement.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MovementRepository extends JpaRepository<Movement, Long>, MovementRepositoryQuery {

    @Query("SELECT DISTINCT m FROM Movement m " +
            "JOIN FETCH m.patient p " +
            "JOIN FETCH m.stockSourceLocation st " +
            "LEFT JOIN FETCH m.stockDestinationLocation std " +
            "JOIN FETCH st.sector s " +
            "JOIN FETCH m.items i " +
            "JOIN FETCH i.batch b " +
            "JOIN FETCH b.product p " +
            "WHERE m.id = :id "
    )
    Optional<Movement> findById(Long id);

    boolean existsById(Long id);

}
