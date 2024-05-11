package com.galdino.rgvpacientes.controller;

import com.galdino.rgvpacientes.dto.SectorFilter;
import com.galdino.rgvpacientes.dto.StockDTO;
import com.galdino.rgvpacientes.model.Sector;
import com.galdino.rgvpacientes.service.SectorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/sectors")
public class SectorController {

    private final SectorService sectorService;

    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @GetMapping("{id}")
    public Sector findById(@PathVariable Long id) {
        return this.sectorService.findById(id);
    }

    @GetMapping
    public List<Sector> getAll(SectorFilter sectorFilter) {
        return this.sectorService.getAll(sectorFilter);
    }

    @GetMapping("{sectorId}/stocks")
    public List<StockDTO> stocksFindBySector(@PathVariable Long sectorId, SectorFilter sectorFilter) {
        sectorFilter.setId(sectorId);
        return this.sectorService.stocksFindBySector(sectorFilter);
    }

}
