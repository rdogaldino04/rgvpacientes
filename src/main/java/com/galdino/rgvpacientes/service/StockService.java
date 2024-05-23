package com.galdino.rgvpacientes.service;

import com.galdino.rgvpacientes.exception.BusinessException;
import com.galdino.rgvpacientes.model.Sector;
import com.galdino.rgvpacientes.model.Stock;
import com.galdino.rgvpacientes.repository.StockRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final SectorService sectorService;

    public StockService(StockRepository stockRepository, SectorService sectorService) {
        this.stockRepository = stockRepository;
        this.sectorService = sectorService;
    }


    public boolean existsById(Long stockId) {
        return this.stockRepository.existsById(stockId);
    }

    @Transactional
    public Stock save(Stock stock) {
        this.sectorService.findById(stock.getSector().getId());
        return this.stockRepository.save(stock);
    }

    @Transactional
    public Stock update(Stock stock) {
        Sector sector = this.sectorService.findById(stock.getSector().getId());
        Stock stockDB = this.findById(stock.getId());
        BeanUtils.copyProperties(stock, stockDB, "id");
        stock.setSector(sector);
        return stock;
    }

    private Stock findById(Long id) {
        return this.stockRepository.findById(id)
                .orElseThrow(() -> new BusinessException(String.format("There is no stock with code %d", id)));
    }
}
