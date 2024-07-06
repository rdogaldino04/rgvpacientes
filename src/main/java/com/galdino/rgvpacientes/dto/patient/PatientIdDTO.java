package com.galdino.rgvpacientes.dto.patient;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PatientIdDTO {

    @NotNull
    private Long id;
}
