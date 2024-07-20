package com.galdino.rgvpacientes.domain.patient.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientIdDTO {

    @NotNull
    private Long id;
}
