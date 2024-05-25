package com.galdino.rgvpacientes.dto.sector;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SectorDTO {

    private Long id;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SectorCompanyDTO company;

}
