package com.galdino.rgvpacientes.dto.movement;

import com.galdino.rgvpacientes.dto.company.CompanyDTO;
import com.galdino.rgvpacientes.dto.movementitem.MovementItemDTO;
import com.galdino.rgvpacientes.dto.patient.PatientMovementDTO;
import com.galdino.rgvpacientes.dto.sector.SectorDTO;
import com.galdino.rgvpacientes.dto.stock.StockDTO;
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
