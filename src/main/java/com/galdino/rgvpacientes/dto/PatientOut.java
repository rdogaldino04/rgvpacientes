package com.galdino.rgvpacientes.dto;

import com.galdino.rgvpacientes.controller.input.AddressOut;

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
