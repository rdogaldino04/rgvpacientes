package com.galdino.rgvpacientes.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PatientFilter {

    private String cpf;

    private String name;

    private String phone;

    private String addressName;

    private String number;

    private String complement;

    private String district;

}
