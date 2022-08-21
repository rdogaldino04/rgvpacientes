package com.galdino.rgvpacientes.service.exception;

public class PatientNotFindException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public PatientNotFindException(String message) {
		super(message);
	}

	public PatientNotFindException(Long cpf) {
		this(String.format("There is no registration of patient with cpf %d", cpf));
	}

}