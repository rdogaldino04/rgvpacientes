package com.galdino.rgvpacientes.domain.movementitem.dto;

import com.galdino.rgvpacientes.domain.batch.dto.BatchMovementItemDTO;
import com.galdino.rgvpacientes.domain.batch.model.Batch;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.OffsetDateTime;

@Deprecated
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
