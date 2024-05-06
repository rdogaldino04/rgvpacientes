package com.galdino.rgvpacientes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovementIdDTO {

    private Long id;

    public MovementIdDTO() {
    }

    public MovementIdDTO(Long id) {
        this.id = id;
    }

}
