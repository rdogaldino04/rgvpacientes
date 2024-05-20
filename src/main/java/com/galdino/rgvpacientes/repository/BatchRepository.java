package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.model.Batch;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BatchRepository extends CustomJpaRepository<Batch, Long>, JpaSpecificationExecutor<Batch> {

    Optional<Batch> findByBatchNumber(String number);

    @Query("from Batch b join fetch b.product")
    List<Batch> findAll();

}
