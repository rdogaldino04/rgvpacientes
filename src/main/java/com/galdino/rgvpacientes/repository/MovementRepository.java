package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MovementRepository extends JpaRepository<Movement, Long> {

    @Query("SELECT DISTINCT m FROM Movement m " +
            "JOIN FETCH m.patient p " +
            "JOIN FETCH m.company c " +
            "JOIN FETCH m.sector s " +
            "JOIN FETCH m.stock st " +
            "JOIN FETCH m.items i " +
            "JOIN FETCH i.material mat " +
            "WHERE m.id = :id "
    )
    Optional<Movement> findById(Long id);

}
