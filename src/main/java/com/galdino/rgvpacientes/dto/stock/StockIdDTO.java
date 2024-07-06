package com.galdino.rgvpacientes.dto.stock;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class StockIdDTO {

    @NotNull
    private Long id;

}
