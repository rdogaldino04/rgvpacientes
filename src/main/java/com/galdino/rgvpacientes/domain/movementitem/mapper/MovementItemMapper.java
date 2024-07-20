package com.galdino.rgvpacientes.domain.movementitem.mapper;

import com.galdino.rgvpacientes.domain.batch.mapper.BatchMapper;
import com.galdino.rgvpacientes.domain.movementitem.dto.MovementItemDTO;
import com.galdino.rgvpacientes.domain.movementitem.model.MovementItem;
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
