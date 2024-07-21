package com.galdino.rgvpacientes.domain.movement.mapper.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.galdino.rgvpacientes.domain.movement.enums.MovementName;
import com.galdino.rgvpacientes.domain.movement.enums.MovementStatus;
import com.galdino.rgvpacientes.domain.movement.enums.MovementType;
import com.galdino.rgvpacientes.domain.stock.mapper.model.StockModel;
import com.galdino.rgvpacientes.domain.user.dto.UserDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.OffsetDateTime;

@Relation(collectionRelation = "movements")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Setter
@Getter
public class MovementModel extends RepresentationModel<MovementModel> {

    @EqualsAndHashCode.Include
    private Long id;

    private OffsetDateTime movementDate;

    private MovementType movementType;

    private MovementName name;

    private StockModel stockSourceLocation;

    private StockModel stockDestinationLocation;

    private MovementModel relatedMovement;

    private UserDTO user;

    private String observation;

    private MovementStatus status;


}
