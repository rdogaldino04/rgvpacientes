package com.galdino.rgvpacientes.service;

import com.galdino.rgvpacientes.model.Stock;
import com.galdino.rgvpacientes.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }


    public boolean existsById(Long stockId) {
        return this.stockRepository.existsById(stockId);
    }
}