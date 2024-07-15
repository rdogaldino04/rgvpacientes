package com.galdino.rgvpacientes.mapper;

import com.galdino.rgvpacientes.dto.sector.SectorCompanyDTO;
import com.galdino.rgvpacientes.dto.sector.SectorDTO;
import com.galdino.rgvpacientes.dto.stock.StockDTO;
import com.galdino.rgvpacientes.dto.stock.StockSaveDTO;
import com.galdino.rgvpacientes.dto.stock.StockWithSectorDTO;
import com.galdino.rgvpacientes.model.Sector;
import com.galdino.rgvpacientes.model.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    public StockDTO toDTO(Stock stock) {
        SectorCompanyDTO companyDTO = null;
        if (stock.getSector().getCompany() != null) {
            companyDTO = SectorCompanyDTO.builder()
                    .id(stock.getSector().getCompany().getId())
                    .name(stock.getSector().getCompany().getName())
                    .build();
        }
        return StockDTO.builder()
                .id(stock.getId())
                .name(stock.getName())
                .sector(SectorDTO.builder()
                        .id(stock.getSector().getId())
                        .name(stock.getSector().getName())
                        .company(companyDTO)
                        .build())
                .build();
    }

    public Stock toEntity(StockSaveDTO stockSaveDTO) {
        return Stock.builder()
                .name(stockSaveDTO.getName())
                .sector(Sector
                        .builder()
                        .id(stockSaveDTO.getSector().getId())
                        .build())
                .build();
    }

    public StockWithSectorDTO toDTOWithSector(Stock stock) {
        return StockWithSectorDTO.builder()
                .id(stock.getId())
                .name(stock.getName())
                .sector(SectorDTO.builder()
                        .id(stock.getSector().getId())
                        .name(stock.getSector().getName())
                        .build())
                .build();
    }

    public Stock toStock(Long stockId) {
        return Stock.builder()
                .id(stockId)
                .build();
    }
}
