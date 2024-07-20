package com.galdino.rgvpacientes.domain.company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

    private Long id;

    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cnpj;

}
