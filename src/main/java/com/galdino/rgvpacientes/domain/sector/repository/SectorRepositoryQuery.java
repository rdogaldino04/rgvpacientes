package com.galdino.rgvpacientes.domain.sector.repository;

import com.galdino.rgvpacientes.domain.sector.dto.SectorDTO;
import com.galdino.rgvpacientes.domain.sector.dto.SectorFilter;
import com.galdino.rgvpacientes.domain.stock.dto.StockDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SectorRepositoryQuery {

    Page<SectorDTO> getAll(SectorFilter sectorFilter, Pageable pageable);

    List<StockDTO> stocksFindBySector(SectorFilter sectorFilter);

}
