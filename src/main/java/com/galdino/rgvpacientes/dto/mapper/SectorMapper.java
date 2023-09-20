package com.galdino.rgvpacientes.dto.mapper;

import com.galdino.rgvpacientes.dto.SectorDTO;
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
