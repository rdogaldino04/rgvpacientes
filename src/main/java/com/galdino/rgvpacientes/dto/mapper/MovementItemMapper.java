package com.galdino.rgvpacientes.dto.mapper;

import com.galdino.rgvpacientes.dto.MovementItemDTO;
import com.galdino.rgvpacientes.model.MovementItem;
import org.springframework.stereotype.Component;

@Component
public class MovementItemMapper {

    private final MaterialMapper materialMapper;

    public MovementItemMapper(MaterialMapper materialMapper) {
        this.materialMapper = materialMapper;
    }

    public MovementItemDTO toDTO(MovementItem item) {
        if (item == null) {
            return null;
        }
        MovementItemDTO itemDTO = new MovementItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setAmount(item.getAmount());
        itemDTO.setMaterial(materialMapper.toMaterialMovementItemDTO(item.getMaterial()));
        return itemDTO;
    }

}
