package com.galdino.rgvpacientes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PatientOut {

	private String cpf;

	private String name;

	private String phone;

	private AddressOut address;

}
