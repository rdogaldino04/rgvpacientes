package com.galdino.rgvpacientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galdino.rgvpacientes.model.Batch;

public interface BatchRepository extends JpaRepository<Batch, Long> {

}
