package com.galdino.rgvpacientes.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.galdino.rgvpacientes.dto.PatientFilter;
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
	public PatientOut findByCpf(@PathVariable @NotBlank String cpf) {
		return this.patientService.findByCpf(cpf);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public PatientOut save(@RequestBody @Valid PatientInput patientInput) {
		return this.patientService.save(patientInput);
	}

	@DeleteMapping("/{cpf}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable @NotBlank String cpf) {
		patientService.delete(cpf);
	}

	@GetMapping
    public ResponseEntity<Page<Patient>> getAllWithPaginate(PatientFilter patientFilter, @PageableDefault(size = 10) Pageable pageable) {
		return ResponseEntity.ok(patientService.getAllWithPaginate(patientFilter, pageable));
    }

}
