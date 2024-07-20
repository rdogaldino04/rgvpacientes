package com.galdino.rgvpacientes.domain.sector.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectorCompanyDTO {

    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

}
