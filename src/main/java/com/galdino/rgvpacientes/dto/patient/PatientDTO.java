package com.galdino.rgvpacientes.dto.patient;

import com.galdino.rgvpacientes.dto.adress.AddressOut;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PatientDTO {

    private long id;

    private String cpf;

    private String name;

    private String phone;

    private AddressOut address;

}
