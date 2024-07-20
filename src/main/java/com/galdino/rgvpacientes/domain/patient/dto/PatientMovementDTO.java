package com.galdino.rgvpacientes.domain.patient.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PatientMovementDTO {

    private long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cpf;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

}
