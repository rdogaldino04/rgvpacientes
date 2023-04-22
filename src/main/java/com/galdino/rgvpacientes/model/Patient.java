package com.galdino.rgvpacientes.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.galdino.rgvpacientes.enums.Status;

import lombok.Data;

@Data
@Entity
@Table(name = "patients")
public class Patient {

	@Id
	private String cpf;

	private String name;

	private String phone;

	@Embedded
	private Address address;

	@NotNull
	private Status status = Status.ACTIVE;

}
