package com.galdino.rgvpacientes.dto.stock;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class StockSectorUpdateDTO {

    @NotBlank
    private Long id;

}
