package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.dto.SectorFilter;
import com.galdino.rgvpacientes.dto.StockDTO;
import com.galdino.rgvpacientes.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SectorRepositoryQuery {

    List<Sector> getAll(SectorFilter sectorFilter);
    List<StockDTO> stocksFindBySector(SectorFilter sectorFilter);

}
