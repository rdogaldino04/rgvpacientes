package com.galdino.rgvpacientes.dto.patient;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PatientMovementDTO {

    private long id;

    private String cpf;

    private String name;

}
