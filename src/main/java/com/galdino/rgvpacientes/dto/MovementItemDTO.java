package com.galdino.rgvpacientes.dto;

import com.galdino.rgvpacientes.dto.material.MaterialMovementItemDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class MovementItemDTO {

    private Long id;

    private MaterialMovementItemDTO material;

    private BigInteger amount;

}
