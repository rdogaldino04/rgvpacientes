package com.galdino.rgvpacientes.dto.product;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilter {

    private Long id;
    private String name;
    private LocalDate createdAt;

}
