package com.galdino.rgvpacientes.domain.stock.repository;

import com.galdino.rgvpacientes.domain.stock.dto.StockFilter;
import com.galdino.rgvpacientes.domain.stock.model.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StockRepositoryQuery {

    Page<Stock> getStockByFilter(StockFilter stockFilter, Pageable pageable);

}
