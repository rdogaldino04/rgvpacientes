package com.galdino.rgvpacientes.domain.sector.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectorSaveDTO {

    @NotBlank
    private String name;

    @NotNull
    private SectorCompanySaveDTO company;

}
