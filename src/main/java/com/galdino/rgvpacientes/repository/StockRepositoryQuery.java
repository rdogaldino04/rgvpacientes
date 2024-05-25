package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.dto.stock.StockFilter;
import com.galdino.rgvpacientes.model.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StockRepositoryQuery {

    Page<Stock> getStockByFilter(StockFilter stockFilter, Pageable pageable);

}
