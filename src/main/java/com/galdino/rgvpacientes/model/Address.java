package com.galdino.rgvpacientes.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address {

	@Column(name = "address_name")
	private String addressName;

	@Column(name = "address_number")
	private String number;

	@Column(name = "address_complement")
	private String complement;

	@Column(name = "address_district")
	private String district;

}