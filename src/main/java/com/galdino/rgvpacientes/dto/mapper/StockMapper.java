package com.galdino.rgvpacientes.dto.mapper;

import com.galdino.rgvpacientes.dto.StockDTO;
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
