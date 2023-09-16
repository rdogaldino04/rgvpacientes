package com.galdino.rgvpacientes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Deprecated
@Getter
@Setter
@Builder
public class MovementDtoId {

    private Long id;

    private List<MovementItemDtoId> itemsDtoIds;


}
