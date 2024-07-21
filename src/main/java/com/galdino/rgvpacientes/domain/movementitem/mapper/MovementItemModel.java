package com.galdino.rgvpacientes.domain.movementitem.mapper;

import com.galdino.rgvpacientes.domain.batch.mapper.BatchModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigInteger;
import java.time.OffsetDateTime;

@Relation(collectionRelation = "movementItems")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Getter
@Setter
public class MovementItemModel extends RepresentationModel<MovementItemModel> {

    @EqualsAndHashCode.Include
    private Long id;

    private BatchModel batch;

    private BigInteger quantity;

    private OffsetDateTime movementItemDate;

}
