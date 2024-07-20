package com.galdino.rgvpacientes.batch.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatchIdDTO {

    @NotNull
    private Long id;

}
