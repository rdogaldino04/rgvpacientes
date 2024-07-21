package com.galdino.rgvpacientes.domain.sector.mapper;

import com.galdino.rgvpacientes.domain.sector.dto.SectorCompanyDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "stocks")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Getter
@Setter
public class SectorModel extends RepresentationModel<SectorModel> {

    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    private SectorCompanyDTO company;

}
