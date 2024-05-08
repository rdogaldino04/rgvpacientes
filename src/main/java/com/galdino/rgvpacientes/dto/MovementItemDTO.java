package com.galdino.rgvpacientes.dto;

import com.galdino.rgvpacientes.dto.product.BatchMovementItemDTO;
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
