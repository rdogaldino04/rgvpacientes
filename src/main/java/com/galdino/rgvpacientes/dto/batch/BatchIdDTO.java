package com.galdino.rgvpacientes.dto.batch;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class BatchIdDTO {

    @NotNull
    private Long id;

}
