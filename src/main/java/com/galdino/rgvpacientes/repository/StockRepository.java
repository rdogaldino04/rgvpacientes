package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long>, StockRepositoryQuery {

    @Query("SELECT s FROM Stock s JOIN FETCH s.sector sec JOIN FETCH sec.company comp WHERE s.id = :stockId")
    Optional<Stock> findByIdWithSector(Long stockId);

}
