package com.galdino.rgvpacientes.dto.sector;

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
