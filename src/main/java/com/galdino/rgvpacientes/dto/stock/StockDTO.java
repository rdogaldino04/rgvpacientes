package com.galdino.rgvpacientes.dto.stock;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StockDTO {

    private Long id;

    private String name;

    public StockDTO() {
    }

    public StockDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
