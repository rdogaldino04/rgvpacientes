package com.galdino.rgvpacientes.mapper;

import com.galdino.rgvpacientes.batch.mapper.BatchMapper;
import com.galdino.rgvpacientes.dto.movementitem.MovementItemDTO;
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
        itemDTO.setBatch(batchMapper.toBatchMovementItemDTO(item.getBatch()));
        itemDTO.setMovementItemDate(item.getMovementItemDate());
        return itemDTO;
    }

}
