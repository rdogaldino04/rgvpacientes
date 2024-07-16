package com.galdino.rgvpacientes.dto.movement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.galdino.rgvpacientes.dto.movementitem.MovementItemDTO;
import com.galdino.rgvpacientes.dto.patient.PatientMovementDTO;
import com.galdino.rgvpacientes.dto.sector.SectorCompanyDTO;
import com.galdino.rgvpacientes.dto.sector.SectorDTO;
import com.galdino.rgvpacientes.dto.stock.StockDTO;
import com.galdino.rgvpacientes.user.dto.UserDTO;
import com.galdino.rgvpacientes.enums.MovementName;
import com.galdino.rgvpacientes.enums.MovementType;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovementDTO {

    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PatientMovementDTO patient;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StockDTO stockSourceLocation;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StockDTO stockDestinationLocation;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MovementItemDTO> items = new ArrayList<>();

    private OffsetDateTime movementDate;

    private MovementType movementType;

    private MovementName name;

    private MovementDTO relatedMovement;

    private UserDTO user;

    private String observation;

    public MovementDTO(Long id, Long patientId, String patientName, Long companyId, String companyName,
                       Long sectorId,
                       String sectorName,
                       Long stockId, String stockName,
                       Long stockIdDest, String stockNameDest,
                       OffsetDateTime movementDate,
                       MovementType movementType) {
        this.id = id;
        this.patient = PatientMovementDTO.builder()
                .id(patientId)
                .name(patientName)
                .build();
        this.stockSourceLocation = StockDTO.builder()
                .id(stockId)
                .name(stockName)
                .sector(SectorDTO.builder()
                        .id(sectorId)
                        .name(sectorName)
                        .company(SectorCompanyDTO.builder()
                                .id(companyId)
                                .name(companyName)
                                .build())
                        .build())
                .build();
        this.stockDestinationLocation = StockDTO.builder()
                .id(stockIdDest)
                .name(stockNameDest)
                .build();
        this.movementDate = movementDate;
        this.movementType = movementType;
    }

}
