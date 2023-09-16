package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
