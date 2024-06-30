package com.galdino.rgvpacientes.dto.movement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovementFilter {

    private Long id;
    private Long patientId;
    private Long companyId;
    private Long sectorId;
    private Long stockId;

}
