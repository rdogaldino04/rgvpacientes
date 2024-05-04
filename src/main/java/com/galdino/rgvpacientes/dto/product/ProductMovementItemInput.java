package com.galdino.rgvpacientes.dto.product;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class ProductMovementItemInput {

    @NotNull
    @Positive
    private Long id;

    private String name;

}
