package com.galdino.rgvpacientes.dto.movementitem;

import com.galdino.rgvpacientes.dto.batch.BatchIdDTO;
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
