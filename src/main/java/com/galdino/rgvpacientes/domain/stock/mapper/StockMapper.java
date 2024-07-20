package com.galdino.rgvpacientes.domain.stock.mapper;

import com.galdino.rgvpacientes.domain.sector.dto.SectorCompanyDTO;
import com.galdino.rgvpacientes.domain.sector.dto.SectorDTO;
import com.galdino.rgvpacientes.domain.stock.dto.StockSaveDTO;
import com.galdino.rgvpacientes.domain.stock.dto.StockWithSectorDTO;
import com.galdino.rgvpacientes.domain.stock.dto.StockDTO;
import com.galdino.rgvpacientes.domain.sector.model.Sector;
import com.galdino.rgvpacientes.domain.stock.model.Stock;
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
