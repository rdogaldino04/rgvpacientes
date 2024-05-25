package com.galdino.rgvpacientes.dto.stock;

import com.galdino.rgvpacientes.dto.sector.SectorDTO;
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
