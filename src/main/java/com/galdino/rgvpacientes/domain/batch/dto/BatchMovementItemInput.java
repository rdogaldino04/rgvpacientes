package com.galdino.rgvpacientes.domain.batch.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class BatchMovementItemInput {

    @NotNull
    @Positive
    private Long id;

    private String batchNumber;

}
