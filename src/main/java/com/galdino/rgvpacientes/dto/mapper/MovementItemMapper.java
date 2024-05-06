package com.galdino.rgvpacientes.dto.mapper;

import com.galdino.rgvpacientes.dto.MovementItemDTO;
import com.galdino.rgvpacientes.model.MovementItem;
import org.springframework.stereotype.Component;

@Component
public class MovementItemMapper {

    private final BatchMapper batchMapper;

    public MovementItemMapper(BatchMapper batchMapper) {
        this.batchMapper = batchMapper;
    }

    public MovementItemDTO toDTO(MovementItem item) {
        if (item == null) {
            return null;
        }
        MovementItemDTO itemDTO = new MovementItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setQuantity(item.getQuantity());
        itemDTO.setProductMovementItem(batchMapper.toBatchMovementItemDTO(item.getBatch()));
        return itemDTO;
    }

}
