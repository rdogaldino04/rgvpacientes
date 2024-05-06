package com.galdino.rgvpacientes.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class MovementInput {

    private Long id;

    @NotNull
    private Long patientId;

    @NotNull
    private Long companyId;

    @NotNull
    private Long sectorId;

    @NotNull
    private Long stockId;

    @Valid
    @NotEmpty
    @Size(min = 1, max = 100)
    private List<MovementItemInput> items;

}
