package com.galdino.rgvpacientes.mapper;

import com.galdino.rgvpacientes.dto.sector.SectorDTO;
import com.galdino.rgvpacientes.model.Sector;
import org.springframework.stereotype.Component;

@Component
public class SectorMapper {

    public SectorDTO toDTO(Sector sector) {
        return SectorDTO.builder()
                .id(sector.getId())
                .name(sector.getName())
                .build();
    }

}
