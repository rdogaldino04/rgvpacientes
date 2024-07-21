package com.galdino.rgvpacientes.domain.stock.mapper.model;

import com.galdino.rgvpacientes.domain.sector.dto.SectorDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "stocks")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Getter
@Setter
public class StockModel extends RepresentationModel<StockModel> {

    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    private SectorDTO sector;

}
