package com.galdino.rgvpacientes.service.exception;

public class PatientNotFindException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public PatientNotFindException(String message) {
		super(message);
	}

}