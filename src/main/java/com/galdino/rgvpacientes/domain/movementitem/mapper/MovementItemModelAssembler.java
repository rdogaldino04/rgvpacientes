package com.galdino.rgvpacientes.domain.movementitem.mapper;

import com.galdino.rgvpacientes.domain.movement.controller.MovementController;
import com.galdino.rgvpacientes.domain.movementitem.model.MovementItem;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class MovementItemModelAssembler extends RepresentationModelAssemblerSupport<MovementItem, MovementItemModel> {

    private final ModelMapper modelMapper;

    public MovementItemModelAssembler(ModelMapper modelMapper) {
        super(MovementController.class, MovementItemModel.class);
        this.modelMapper = modelMapper;
    }

    @NonNull
    @Override
    public MovementItemModel toModel(@NonNull MovementItem entity) {
        MovementItemModel movementItemModel = createModelWithId(entity.getId(), entity);
        modelMapper.map(entity, movementItemModel);
        return movementItemModel;
    }

    @NonNull
    @Override
    public CollectionModel<MovementItemModel> toCollectionModel(@NonNull Iterable<? extends MovementItem> entities) {
        CollectionModel<MovementItemModel> movementItemModels = super.toCollectionModel(entities);
        return movementItemModels;
    }


}
