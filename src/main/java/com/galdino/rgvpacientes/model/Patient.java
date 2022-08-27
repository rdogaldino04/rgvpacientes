package com.galdino.rgvpacientes.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Patient {

	@Id
	private String cpf;

	private String name;

}
