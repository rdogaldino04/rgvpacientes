package com.galdino.rgvpacientes.domain.stock.dto;

import com.galdino.rgvpacientes.domain.sector.dto.SectorDTO;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockWithSectorDTO {

    private Long id;

    private String name;

    private SectorDTO sector;

}
