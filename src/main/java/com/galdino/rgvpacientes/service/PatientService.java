package com.galdino.rgvpacientes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galdino.rgvpacientes.model.Patient;
import com.galdino.rgvpacientes.repository.PatientRepository;
import com.galdino.rgvpacientes.service.exception.PatientNotFindException;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patienteRepository;

	public Patient findByCpf(Long cpf) {
		return this.patienteRepository
				.findById(cpf)
				.orElseThrow(() -> new PatientNotFindException(cpf));
	}

}
