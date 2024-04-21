package com.galdino.rgvpacientes.dto;

import java.time.LocalDate;

import com.galdino.rgvpacientes.dto.material.MaterialDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BatchDTO {

    private Long id;

    private String batchNumber;

    private LocalDate manufactureDate;

    private LocalDate expiryDate;

    private MaterialDTO material;

}
