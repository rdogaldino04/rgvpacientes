package com.galdino.rgvpacientes.domain.stock.dto;

import com.galdino.rgvpacientes.domain.sector.dto.SectorDTO;
import lombok.*;

@Deprecated
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {

    private Long id;

    private String name;

    private SectorDTO sector;

}
