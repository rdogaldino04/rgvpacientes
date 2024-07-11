package com.galdino.rgvpacientes.dto.stock;

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
