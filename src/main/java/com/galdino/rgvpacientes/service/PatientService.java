package com.galdino.rgvpacientes.service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galdino.rgvpacientes.dto.PatientFilter;
import com.galdino.rgvpacientes.dto.PatientInput;
import com.galdino.rgvpacientes.dto.PatientOut;
import com.galdino.rgvpacientes.dto.mapper.PatientMapper;
import com.galdino.rgvpacientes.dto.specs.PatientSpecs;
import com.galdino.rgvpacientes.dto.wrapper.PageWrapper;
import com.galdino.rgvpacientes.model.Patient;
import com.galdino.rgvpacientes.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patienteRepository;

	@Autowired
	private PatientMapper patientMapper;

	@Autowired
	private Environment env;

	public PatientOut findByCpf(String cpf) {
		return this.patientMapper.toDTO(this.patienteRepository.findById(cpf)
				.orElseThrow(() -> new EntityNotFoundException(cpf)));
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

	public PageWrapper<Patient> getAllWithPaginate(PatientFilter patientFilter, Pageable pageable) {
		String databaseUrl = env.getProperty("spring.datasource.url");
		if (databaseUrl != null && databaseUrl.startsWith("jdbc:sqlite:")) {
			return new PageWrapper<>(this.patienteRepository.getAllWithPaginate(patientFilter, pageable));
		}
		Page<Patient> patientPage = this.patienteRepository.findAll(PatientSpecs.usingFilter(patientFilter), pageable);
		return new PageWrapper<>(patientPage);
	}

}
