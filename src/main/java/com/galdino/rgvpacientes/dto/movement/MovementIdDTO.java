package com.galdino.rgvpacientes.dto.movement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovementIdDTO {

    private Long id;

    public MovementIdDTO(Long id) {
        this.id = id;
    }

}
