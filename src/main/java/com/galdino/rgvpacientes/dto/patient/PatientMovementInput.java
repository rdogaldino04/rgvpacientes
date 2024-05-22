package com.galdino.rgvpacientes.dto.patient;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class PatientMovementInput {

    @NotNull
    @Positive
    private Long id;

    private String name;

    private String cpf;

}
