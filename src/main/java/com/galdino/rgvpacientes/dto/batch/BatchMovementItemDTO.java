package com.galdino.rgvpacientes.dto.batch;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatchMovementItemDTO {

    private Long id;
    private String batchNumber;

}
