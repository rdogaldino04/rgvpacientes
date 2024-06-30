package com.galdino.rgvpacientes.dto.movementitem;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;
import java.time.OffsetDateTime;

@Getter
@Setter
public class MovementItemInput {

    private Long id;

    @NotNull
    @Valid
    private Long batchId;

    @NotNull
    @Positive
    private BigInteger quantity;

    @NotNull
    private OffsetDateTime movementItemDate;

}
