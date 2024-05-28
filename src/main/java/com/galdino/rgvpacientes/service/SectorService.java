package com.galdino.rgvpacientes.service;

import com.galdino.rgvpacientes.dto.sector.SectorDTO;
import com.galdino.rgvpacientes.dto.sector.SectorFilter;
import com.galdino.rgvpacientes.dto.stock.StockDTO;
import com.galdino.rgvpacientes.model.Sector;
import com.galdino.rgvpacientes.repository.SectorRepository;
import com.galdino.rgvpacientes.util.page.PageWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SectorService {

    private final SectorRepository sectorRepository;
    private final CompanyService companyService;

    public SectorService(SectorRepository sectorRepository, CompanyService companyService) {
        this.sectorRepository = sectorRepository;
        this.companyService = companyService;
    }

    public PageWrapper<SectorDTO> getAll(SectorFilter sectorFilter, Pageable pageable) {
        Page<SectorDTO> page = this.sectorRepository.getAll(sectorFilter, pageable);
        return new PageWrapper<>(page);
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
