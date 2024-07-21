package com.galdino.rgvpacientes.domain.batch.dto;

import lombok.*;

@Deprecated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatchMovementItemDTO {

    private Long id;
    private String batchNumber;

}
