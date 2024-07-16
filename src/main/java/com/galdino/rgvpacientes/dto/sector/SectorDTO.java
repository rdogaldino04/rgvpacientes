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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SectorCompanyDTO company;

    public SectorDTO(Long id, String name, Long companyId, String companyName) {
        this.id = id;
        this.name = name;
        this.company = new SectorCompanyDTO(companyId, companyName);
    }
}
