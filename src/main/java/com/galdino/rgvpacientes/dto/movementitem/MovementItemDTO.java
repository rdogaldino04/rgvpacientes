package com.galdino.rgvpacientes.dto.movementitem;

import com.galdino.rgvpacientes.batch.dto.BatchMovementItemDTO;
import com.galdino.rgvpacientes.batch.model.Batch;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.OffsetDateTime;

@Getter
@Setter
public class MovementItemDTO {

    private Long id;

    private BatchMovementItemDTO batch;

    private BigInteger quantity;

    private OffsetDateTime movementItemDate;

    public MovementItemDTO() {
    }

    public MovementItemDTO(Long id, Batch batch, BigInteger quantity, OffsetDateTime movementItemDate) {
        this.id = id;
        this.batch = BatchMovementItemDTO.builder()
                .id(batch.getId())
                .batchNumber(batch.getBatchNumber())
                .build();
        this.quantity = quantity;
        this.movementItemDate = movementItemDate;
    }
}
