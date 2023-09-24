package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.dto.SectorFilter;
import com.galdino.rgvpacientes.dto.StockDTO;
import com.galdino.rgvpacientes.model.Sector;

import java.util.List;

public interface SectorRepositoryQuery {

    List<Sector> getAll(SectorFilter sectorFilter);

    List<StockDTO> stocksFindBySector(SectorFilter sectorFilter);

}
