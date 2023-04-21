package com.galdino.rgvpacientes.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.galdino.rgvpacientes.dto.PatientInput;
import com.galdino.rgvpacientes.dto.PatientOut;
import com.galdino.rgvpacientes.model.Patient;
import com.galdino.rgvpacientes.service.PatientService;

@RestController
@RequestMapping("patients")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@GetMapping("/{cpf}")
	public Patient findByCpf(@PathVariable String cpf) {
		return this.patientService.findByCpf(cpf);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public PatientOut save(@RequestBody @Valid PatientInput patientInput) {
		return this.patientService.save(patientInput);
	}

}
