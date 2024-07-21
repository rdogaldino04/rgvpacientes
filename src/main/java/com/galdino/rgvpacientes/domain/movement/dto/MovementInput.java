package com.galdino.rgvpacientes.domain.movement.dto;

import com.galdino.rgvpacientes.domain.movement.enums.MovementName;
import com.galdino.rgvpacientes.domain.movement.enums.MovementStatus;
import com.galdino.rgvpacientes.domain.movement.enums.MovementType;
import com.galdino.rgvpacientes.domain.movementitem.dto.MovementItemInput;
import com.galdino.rgvpacientes.domain.patient.dto.PatientIdDTO;
import com.galdino.rgvpacientes.domain.stock.dto.StockIdDTO;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovementInput {

    private Long id;

    @NotNull
    private PatientIdDTO patient;

    @NotNull
    private StockIdDTO stockSourceLocation;

    private StockIdDTO stockDestinationLocation;

    @Valid
    @NotEmpty
    @Size(min = 1, max = 100)
    private List<MovementItemInput> items;

    private OffsetDateTime movementDate;

    @NotNull
    private MovementType movementType;

    @NotNull
    private MovementName name;

    private String observation;

    @NotNull
    private MovementStatus status;

}
