package com.galdino.rgvpacientes.dto.patient;

import javax.validation.constraints.NotBlank;

import com.galdino.rgvpacientes.dto.adress.AddressInput;
import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientInput {

	private Long id;
	
	@NotBlank(message = "CPF é obrigatório")
	@CPF
	private String cpf;

	@NotBlank(message = "NOME é obrigatório")
	private String name;

	private String phone;

	private AddressInput address;

}
