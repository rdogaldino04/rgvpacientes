package com.galdino.rgvpacientes.domain.stock.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockIdDTO {

    @NotNull
    private Long id;

}
