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

import com.galdino.rgvpacientes.controller.input.PatientInput;
import com.galdino.rgvpacientes.model.Address;
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
	public Patient save(@RequestBody @Valid PatientInput patientInput) {
		return this.patientService.save(toPatient(patientInput));
	}

	private Patient toPatient(PatientInput patientInput) {
		Patient patient = new Patient();
		patient.setCpf(patientInput.getCpf());
		patient.setName(patientInput.getName());
		patient.setPhone(patientInput.getPhone());
		Address address = new Address();
		if (patientInput.getAddress() != null) {
			address.setAddressName(patientInput.getAddress().getAddressName());
			address.setComplement(patientInput.getAddress().getComplement());
			address.setDistrict(patientInput.getAddress().getDistrict());
			address.setNumber(patientInput.getAddress().getNumber());
			patient.setAddress(address);
		}
		return patient;
	}

}
