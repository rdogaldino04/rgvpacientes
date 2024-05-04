package com.galdino.rgvpacientes.dto.product;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private LocalDate expirationDate;
    private LocalDate createdAt;

}
