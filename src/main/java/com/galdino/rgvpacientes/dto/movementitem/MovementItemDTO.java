package com.galdino.rgvpacientes.dto.movementitem;

import com.galdino.rgvpacientes.dto.batch.BatchMovementItemDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class MovementItemDTO {

    private Long id;

    private BatchMovementItemDTO batch;

    private BigInteger quantity;

}
