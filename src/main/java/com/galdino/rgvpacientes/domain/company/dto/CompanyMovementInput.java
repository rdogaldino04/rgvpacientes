package com.galdino.rgvpacientes.domain.company.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class CompanyMovementInput {

    @NotNull
    @Positive
    private Long id;

    private String name;

}
