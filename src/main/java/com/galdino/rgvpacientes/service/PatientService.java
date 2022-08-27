package com.galdino.rgvpacientes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galdino.rgvpacientes.model.Patient;
import com.galdino.rgvpacientes.repository.PatientRepository;
import com.galdino.rgvpacientes.service.exception.PatientNotFindException;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patienteRepository;

	public Patient findByCpf(String cpf) {
		return this.patienteRepository.findById(cpf).orElseThrow(() -> new PatientNotFindException(cpf));
	}

	@Transactional(rollbackFor = Exception.class)
	public Patient save(Patient patient) {
		return patienteRepository.save(patient);
	}

}
