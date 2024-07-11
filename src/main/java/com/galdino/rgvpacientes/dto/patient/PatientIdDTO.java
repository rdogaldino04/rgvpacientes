package com.galdino.rgvpacientes.dto.patient;

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
