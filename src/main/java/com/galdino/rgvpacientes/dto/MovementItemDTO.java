package com.galdino.rgvpacientes.dto;

import com.galdino.rgvpacientes.dto.product.ProductMovementItemDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class MovementItemDTO {

    private Long id;

    private ProductMovementItemDTO productMovementItem;

    private BigInteger amount;

}
