package com.galdino.rgvpacientes.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galdino.rgvpacientes.dto.sector.SectorFilter;
import com.galdino.rgvpacientes.dto.stock.StockDTO;
import com.galdino.rgvpacientes.model.Sector;
import com.galdino.rgvpacientes.repository.SectorRepository;

@Service
public class SectorService {

    private final SectorRepository sectorRepository;
    private final CompanyService companyService;

    public SectorService(SectorRepository sectorRepository, CompanyService companyService) {
        this.sectorRepository = sectorRepository;
        this.companyService = companyService;
    }

    public List<Sector> getAll(SectorFilter sectorFilter) {
        return this.sectorRepository.getAll(sectorFilter);
    }

    public List<StockDTO> stocksFindBySector(SectorFilter sectorFilter) {
        return this.sectorRepository.stocksFindBySector(sectorFilter);
    }

    public Sector findById(Long id) {
        return this.sectorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no sector with code %d", id)));
    }

    public boolean existsById(Long sectorId) {
        return this.sectorRepository.existsById(sectorId);
    }

    @Transactional
    public Sector save(Sector sector) {
        this.companyService.findById(sector.getCompany().getId());
        return this.sectorRepository.save(sector);
    }

    @Transactional
    public Sector update(Sector sector) {
        this.companyService.findById(sector.getCompany().getId());
        Sector sectorDB = this.findById(sector.getId());
        BeanUtils.copyProperties(sector, sectorDB, "id");
        return sector;
    }
}
