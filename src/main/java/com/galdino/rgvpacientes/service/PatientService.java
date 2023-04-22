package com.galdino.rgvpacientes.service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galdino.rgvpacientes.dto.PatientInput;
import com.galdino.rgvpacientes.dto.PatientOut;
import com.galdino.rgvpacientes.dto.mapper.PatientMapper;
import com.galdino.rgvpacientes.model.Patient;
import com.galdino.rgvpacientes.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patienteRepository;

	@Autowired
	private PatientMapper patientMapper;

	public Patient findByCpf(String cpf) {
		return this.patienteRepository.findById(cpf).orElseThrow(() -> new EntityNotFoundException(cpf));
	}

	@Transactional(rollbackFor = Exception.class)
	public PatientOut save(PatientInput patientInput) {
		Patient patient = patienteRepository.save(patientMapper.toModel(patientInput));
		return patientMapper.toDTO(patient);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(@NotBlank String cpf) {
		patienteRepository.delete(
				patienteRepository.findById(cpf)
						.orElseThrow(() -> new EntityNotFoundException(cpf)));
	}

}
