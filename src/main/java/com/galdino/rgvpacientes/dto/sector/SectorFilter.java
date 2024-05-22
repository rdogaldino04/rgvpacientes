package com.galdino.rgvpacientes.dto.sector;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SectorFilter {

    private Long id;

    private String name;

    private Long stockId;

    private String stockName;

    private Long companyId;

}
