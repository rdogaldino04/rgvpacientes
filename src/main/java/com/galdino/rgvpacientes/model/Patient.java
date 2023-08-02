package com.galdino.rgvpacientes.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;

import com.galdino.rgvpacientes.enums.Status;
import com.galdino.rgvpacientes.enums.converters.StatusConverter;

import lombok.Data;

@Data
@Entity
@Table(name = "patients", schema = "dbapatient")
@SQLDelete(sql = "UPDATE dbapatient.patients SET status = 'Inactive' WHERE id = ?")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String cpf;

	private String name;

	private String phone;

	@Embedded
	private Address address;

	@NotNull
	@Convert(converter = StatusConverter.class)
	private Status status = Status.ACTIVE;

}
