package com.galdino.rgvpacientes.domain.movement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Deprecated
@Getter
@Setter
@NoArgsConstructor
public class MovementIdDTO {

    private Long id;

    public MovementIdDTO(Long id) {
        this.id = id;
    }

}
