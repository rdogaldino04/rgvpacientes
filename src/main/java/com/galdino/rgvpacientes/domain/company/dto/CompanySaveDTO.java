package com.galdino.rgvpacientes.domain.company.dto;

import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanySaveDTO {

    @NotBlank
    private String name;

    @NotBlank
    @CNPJ
    private String cnpj;

}
