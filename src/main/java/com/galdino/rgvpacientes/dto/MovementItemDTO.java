package com.galdino.rgvpacientes.dto;

import com.galdino.rgvpacientes.model.Material;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class MovementItemDTO {

    private Long id;

    private MaterialDTO material;

    private BigInteger amount;

}
