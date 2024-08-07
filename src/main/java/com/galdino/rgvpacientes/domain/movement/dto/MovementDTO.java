package com.galdino.rgvpacientes.domain.movement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.galdino.rgvpacientes.domain.movement.enums.MovementName;
import com.galdino.rgvpacientes.domain.movement.enums.MovementStatus;
import com.galdino.rgvpacientes.domain.movement.enums.MovementType;
import com.galdino.rgvpacientes.domain.movementitem.dto.MovementItemDTO;
import com.galdino.rgvpacientes.domain.patient.dto.PatientMovementDTO;
import com.galdino.rgvpacientes.domain.sector.dto.SectorCompanyDTO;
import com.galdino.rgvpacientes.domain.sector.dto.SectorDTO;
import com.galdino.rgvpacientes.domain.stock.dto.StockDTO;
import com.galdino.rgvpacientes.domain.user.dto.UserDTO;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Deprecated
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

    private MovementStatus status;

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
