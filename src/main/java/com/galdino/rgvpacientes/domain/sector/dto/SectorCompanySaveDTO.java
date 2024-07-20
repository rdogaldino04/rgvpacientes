package com.galdino.rgvpacientes.domain.sector.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SectorCompanySaveDTO {

    @NotNull
    private Long id;

}
