package com.galdino.rgvpacientes.dto.company;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

    private Long id;

    private String name;

    private String cnpj;

}
