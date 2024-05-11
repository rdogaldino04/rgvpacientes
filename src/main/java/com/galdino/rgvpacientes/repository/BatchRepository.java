package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.model.Batch;

import java.util.Optional;

public interface BatchRepository extends CustomJpaRepository<Batch, Long> {

    Optional<Batch> findByBatchNumber(String number);

}
