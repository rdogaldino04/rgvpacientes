package com.galdino.rgvpacientes.mapper;

import com.galdino.rgvpacientes.dto.sector.SectorCompanyDTO;
import com.galdino.rgvpacientes.dto.sector.SectorDTO;
import com.galdino.rgvpacientes.dto.sector.SectorSaveDTO;
import com.galdino.rgvpacientes.model.Company;
import com.galdino.rgvpacientes.model.Sector;
import org.springframework.stereotype.Component;

@Component
public class SectorMapper {

    public SectorDTO toDTO(Sector sector) {
        SectorDTO sectorDTO = new SectorDTO();
        sectorDTO.setId(sector.getId());
        sectorDTO.setName(sector.getName());
        SectorCompanyDTO companyDTO = new SectorCompanyDTO();
        companyDTO.setId(sector.getCompany().getId());
        companyDTO.setName(sector.getCompany().getName());
        sectorDTO.setCompany(companyDTO);
        return sectorDTO;
    }

    public Sector toEntity(SectorSaveDTO sectorSaveDTO) {
        Sector sector = new Sector();
        sector.setName(sectorSaveDTO.getName());
        sector.setCompany(new Company(sectorSaveDTO.getCompany().getId()));
        return sector;
    }

    public Sector toSector(Long sectorId) {
        Sector sector = new Sector();
        sector.setId(sectorId);
        return sector;
    }
}
