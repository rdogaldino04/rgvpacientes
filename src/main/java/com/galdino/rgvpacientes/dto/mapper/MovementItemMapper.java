package com.galdino.rgvpacientes.dto.mapper;

import com.galdino.rgvpacientes.dto.MovementItemDTO;
import com.galdino.rgvpacientes.model.MovementItem;
import org.springframework.stereotype.Component;

@Component
public class MovementItemMapper {

    private final ProductMapper productMapper;

    public MovementItemMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public MovementItemDTO toDTO(MovementItem item) {
        if (item == null) {
            return null;
        }
        MovementItemDTO itemDTO = new MovementItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setAmount(item.getAmount());
        itemDTO.setProductMovementItem(productMapper.toProductMovementItemDTO(item.getProduct()));
        return itemDTO;
    }

}
