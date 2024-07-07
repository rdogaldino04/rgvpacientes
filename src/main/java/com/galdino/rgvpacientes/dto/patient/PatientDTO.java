package com.galdino.rgvpacientes.dto.patient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.galdino.rgvpacientes.dto.adress.AddressOut;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PatientDTO {

    private long id;

    private String cpf;

    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phone;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AddressOut address;

}
