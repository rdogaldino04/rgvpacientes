package com.galdino.rgvpacientes.dto.company;

import lombok.*;

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
    private String cnpj;

}
