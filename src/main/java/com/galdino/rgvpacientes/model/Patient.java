package com.galdino.rgvpacientes.model;

import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;

import com.galdino.rgvpacientes.enums.Status;
import com.galdino.rgvpacientes.enums.converters.StatusConverter;

import lombok.Data;

@Data
@Entity
@Table(name = "patients")
@SQLDelete(sql = "UPDATE patients SET status = 'Inactive' WHERE cpf = ?")
public class Patient {

	@Id
	private String cpf;

	private String name;

	private String phone;

	@Embedded
	private Address address;

	@NotNull
	@Convert(converter = StatusConverter.class)
	private Status status = Status.ACTIVE;

}
