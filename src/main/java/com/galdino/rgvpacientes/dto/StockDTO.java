package com.galdino.rgvpacientes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
