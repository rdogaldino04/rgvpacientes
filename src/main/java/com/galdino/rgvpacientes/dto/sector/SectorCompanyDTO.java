package com.galdino.rgvpacientes.dto.sector;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectorCompanyDTO {

    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    public SectorCompanyDTO() {
    }

    public SectorCompanyDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
