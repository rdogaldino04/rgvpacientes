package com.galdino.rgvpacientes.controller;

import com.galdino.rgvpacientes.dto.sector.SectorDTO;
import com.galdino.rgvpacientes.dto.sector.SectorFilter;
import com.galdino.rgvpacientes.dto.sector.SectorSaveDTO;
import com.galdino.rgvpacientes.dto.stock.StockDTO;
import com.galdino.rgvpacientes.mapper.SectorMapper;
import com.galdino.rgvpacientes.model.Sector;
import com.galdino.rgvpacientes.service.SectorService;
import com.galdino.rgvpacientes.util.page.PageWrapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/sectors")
public class SectorController {

    private final SectorService sectorService;
    private final SectorMapper sectorMapper;

    public SectorController(SectorService sectorService, SectorMapper sectorMapper) {
        this.sectorService = sectorService;
        this.sectorMapper = sectorMapper;
    }

    @GetMapping("{id}")
    public SectorDTO findById(@PathVariable Long id) {
        return this.sectorMapper.toDTO(this.sectorService.findById(id));
    }

    @GetMapping
    public PageWrapper<SectorDTO> getAll(SectorFilter sectorFilter, @PageableDefault(size = 5) Pageable pageable) {
        return this.sectorService.getAll(sectorFilter, pageable);
    }

    @GetMapping("{sectorId}/stocks")
    public List<StockDTO> stocksFindBySector(@PathVariable Long sectorId, SectorFilter sectorFilter) {
        sectorFilter.setId(sectorId);
        return this.sectorService.stocksFindBySector(sectorFilter);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SectorDTO save(@RequestBody @Valid SectorSaveDTO sectorSaveDTO) {
        return this.sectorMapper.toDTO(this.sectorService.save(this.sectorMapper.toEntity(sectorSaveDTO)));
    }

    @PutMapping("{id}")
    public SectorDTO update(@PathVariable Long id, @RequestBody @Valid SectorSaveDTO sectorSaveDTO) {
        Sector sector = this.sectorMapper.toEntity(sectorSaveDTO);
        sector.setId(id);
        return this.sectorMapper.toDTO(this.sectorService.update(sector));
    }

}
