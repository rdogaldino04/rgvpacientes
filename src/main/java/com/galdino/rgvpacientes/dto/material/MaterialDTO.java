package com.galdino.rgvpacientes.dto.material;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDTO {

    private Long id;
    private String name;
    private LocalDate expirationDate;
    private LocalDate registrationDate;

}
