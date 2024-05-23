package com.galdino.rgvpacientes.dto.sector;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SectorCompanySaveDTO {

    @NotNull
    private Long id;

}
