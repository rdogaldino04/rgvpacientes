package com.galdino.rgvpacientes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galdino.rgvpacientes.model.Patient;
import com.galdino.rgvpacientes.service.PatientService;

@RestController
@RequestMapping("patients")
public class PatientController {
	
	@Autowired
	private PatientService patientService;

	@GetMapping("/{cpf}")
	public Patient findByCpf(@PathVariable Long cpf) {
		return this.patientService.findByCpf(cpf);
	}

}
