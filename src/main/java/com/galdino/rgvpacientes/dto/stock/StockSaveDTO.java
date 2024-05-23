package com.galdino.rgvpacientes.dto.stock;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class StockSaveDTO {

    @NotBlank
    private String name;

    @NotNull
     private Long sectorId;
}
