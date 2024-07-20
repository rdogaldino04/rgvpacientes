package com.galdino.rgvpacientes.domain.patient.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.galdino.rgvpacientes.domain.address.dto.AddressOut;
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
