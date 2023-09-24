package com.galdino.rgvpacientes.dto.material;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaterialInput {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @Future
    private LocalDate expirationDate;

}
