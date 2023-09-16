package com.galdino.rgvpacientes.service;

import com.galdino.rgvpacientes.dto.SectorFilter;
import com.galdino.rgvpacientes.dto.StockDTO;
import com.galdino.rgvpacientes.model.Sector;
import com.galdino.rgvpacientes.repository.SectorRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SectorService {

    private final SectorRepository sectorRepository;

    public SectorService(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    public List<Sector> getAll(SectorFilter sectorFilter) {
        return this.sectorRepository.getAll(sectorFilter);
    }

    public List<StockDTO> stocksFindBySector(SectorFilter sectorFilter) {
        return this.sectorRepository.stocksFindBySector(sectorFilter);
    }

    public Sector findById(Long id) {
        return this.sectorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("There is no company with code %d", id)));
    }

    public boolean existsById(Long sectorId) {
        return this.sectorRepository.existsById(sectorId);
    }
}
