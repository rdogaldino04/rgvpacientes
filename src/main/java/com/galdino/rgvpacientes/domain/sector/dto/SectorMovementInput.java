package com.galdino.rgvpacientes.domain.sector.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class SectorMovementInput {

    @NotNull
    @Positive
    private Long id;

    private String name;

}
