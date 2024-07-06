package com.galdino.rgvpacientes.dto.movement;

import com.galdino.rgvpacientes.dto.movementitem.MovementItemInput;
import com.galdino.rgvpacientes.enums.MovementType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
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

    private OffsetDateTime movementDate;

    @NotNull
    private MovementType movementType;

}
