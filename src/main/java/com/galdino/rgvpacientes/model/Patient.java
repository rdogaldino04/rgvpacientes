package com.galdino.rgvpacientes.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.galdino.rgvpacientes.enums.Status;

import lombok.Data;

@Data
@Entity
@Table(name = "patients")
@SQLDelete(sql = "UPDATE patients SET status = 'Inactive' WHERE cpf = ?")
@Where(clause = "status = 'Active'")
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
