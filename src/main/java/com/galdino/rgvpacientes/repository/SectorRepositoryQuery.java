package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.dto.sector.SectorDTO;
import com.galdino.rgvpacientes.dto.sector.SectorFilter;
import com.galdino.rgvpacientes.dto.stock.StockDTO;
import com.galdino.rgvpacientes.model.Sector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SectorRepositoryQuery {

    Page<SectorDTO> getAll(SectorFilter sectorFilter, Pageable pageable);

    List<StockDTO> stocksFindBySector(SectorFilter sectorFilter);

}
