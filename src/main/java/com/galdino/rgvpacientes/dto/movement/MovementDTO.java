package com.galdino.rgvpacientes.dto.movement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.galdino.rgvpacientes.dto.movementitem.MovementItemDTO;
import com.galdino.rgvpacientes.dto.patient.PatientMovementDTO;
import com.galdino.rgvpacientes.dto.stock.StockDTO;
import com.galdino.rgvpacientes.dto.user.UserDTO;
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
    private StockDTO stock;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MovementItemDTO> items = new ArrayList<>();

    private OffsetDateTime movementDate;

    private MovementType movementType;

    private MovementName name;

    private MovementDTO relatedMovement;

    private UserDTO user;

    private String observation;

}
