package com.galdino.rgvpacientes.domain.movementitem.dto;

import com.galdino.rgvpacientes.domain.batch.dto.BatchIdDTO;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovementItemInput {

    private Long id;

    @NotNull
    @Valid
    private BatchIdDTO batch;

    @NotNull
    @Positive
    private BigInteger quantity;

    private OffsetDateTime movementItemDate;

}
