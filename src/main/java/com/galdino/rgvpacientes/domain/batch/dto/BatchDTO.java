package com.galdino.rgvpacientes.domain.batch.dto;

import java.time.LocalDate;

import com.galdino.rgvpacientes.domain.product.product.ProductDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BatchDTO {

    private Long id;

    private String batchNumber;

    private LocalDate manufactureDate;

    private LocalDate expiryDate;

    private ProductDTO product;

}
