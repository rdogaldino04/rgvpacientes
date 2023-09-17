package com.galdino.rgvpacientes.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MovementDTO {

    private Long id;

    private PatientMovementDTO patient;

    private CompanyDTO company;

    private SectorDTO sector;

    private StockDTO stock;

    private List<MovementItemDTO> items = new ArrayList<>();

}
