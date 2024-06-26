package com.galdino.rgvpacientes.dto.movement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.galdino.rgvpacientes.dto.company.CompanyDTO;
import com.galdino.rgvpacientes.dto.movementitem.MovementItemDTO;
import com.galdino.rgvpacientes.dto.patient.PatientMovementDTO;
import com.galdino.rgvpacientes.dto.sector.SectorDTO;
import com.galdino.rgvpacientes.dto.stock.StockDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MovementDTO {

    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PatientMovementDTO patient;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CompanyDTO company;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SectorDTO sector;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StockDTO stock;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MovementItemDTO> items = new ArrayList<>();

    private OffsetDateTime movementDate;

    public MovementDTO() {
    }

    public MovementDTO(Long id, Long patientId, String patientName, Long companyId, String companyName, Long sectorId,
                       String sectorName, Long stockId, String stockName, OffsetDateTime movementDate) {
        this.id = id;
        this.patient = PatientMovementDTO.builder()
                .id(patientId)
                .name(patientName)
                .build();
        this.company = CompanyDTO.builder()
                .id(companyId)
                .name(companyName)
                .build();
        this.sector = SectorDTO.builder()
                .id(sectorId)
                .name(sectorName)
                .build();
        this.stock = StockDTO.builder()
                .id(stockId)
                .name(stockName)
                .build();
        this.movementDate = movementDate;
    }

}
