package com.galdino.rgvpacientes.domain.batch.mapper;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "batchs")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Getter
@Setter
public class BatchModel extends RepresentationModel<BatchModel> {

    @EqualsAndHashCode.Include
    private Long id;
    private String batchNumber;

}
