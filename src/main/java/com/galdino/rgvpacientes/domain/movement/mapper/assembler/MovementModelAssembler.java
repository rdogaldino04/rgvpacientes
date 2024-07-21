package com.galdino.rgvpacientes.domain.movement.mapper.assembler;

import com.galdino.rgvpacientes.domain.movement.controller.MovementController;
import com.galdino.rgvpacientes.domain.movement.mapper.model.MovementModel;
import com.galdino.rgvpacientes.domain.movement.model.Movement;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class MovementModelAssembler extends RepresentationModelAssemblerSupport<Movement, MovementModel> {

    private final ModelMapper modelMapper;

    public MovementModelAssembler(ModelMapper modelMapper) {
        super(MovementController.class, MovementModel.class);
        this.modelMapper = modelMapper;
    }

    @Override
    @NonNull
    public MovementModel toModel(@NonNull Movement movement) {
        MovementModel movementModel = createModelWithId(movement.getId(), movement);
        this.modelMapper.map(movement, movementModel);
        return movementModel;
    }
}
