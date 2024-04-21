package com.galdino.rgvpacientes.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.galdino.rgvpacientes.dto.material.MaterialDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchInput {

    @NotBlank
    private String batchNumber;

    @NotNull
    private LocalDate manufactureDate;

    private LocalDate expiryDate;

    @NotNull
    private MaterialDTO material;

}
