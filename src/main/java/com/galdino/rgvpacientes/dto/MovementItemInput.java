package com.galdino.rgvpacientes.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;

@Getter
@Setter
public class MovementItemInput {

    private Long id;

    @NotNull
    @Valid
    private MaterialMovementItemInput material;

    @NotNull
    @Positive
    private BigInteger amount;

}
