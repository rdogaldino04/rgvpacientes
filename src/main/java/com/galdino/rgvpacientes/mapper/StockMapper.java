package com.galdino.rgvpacientes.mapper;

import com.galdino.rgvpacientes.dto.stock.StockDTO;
import com.galdino.rgvpacientes.model.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    public StockDTO toDTO(Stock stock) {
        return StockDTO.builder()
                .id(stock.getId())
                .name(stock.getName())
                .build();
    }

}
