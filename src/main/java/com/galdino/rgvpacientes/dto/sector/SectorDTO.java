package com.galdino.rgvpacientes.dto.sector;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SectorDTO {

    private Long id;
    private String name;
    private SectorCompanyDTO company;

}
