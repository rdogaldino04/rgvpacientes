package com.galdino.rgvpacientes.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.galdino.rgvpacientes.dto.patient.PatientFilter;
import com.galdino.rgvpacientes.dto.patient.PatientInput;
import com.galdino.rgvpacientes.dto.patient.PatientDTO;
import com.galdino.rgvpacientes.util.page.PageWrapper;
import com.galdino.rgvpacientes.model.Patient;
import com.galdino.rgvpacientes.service.PatientService;

@RestController
@RequestMapping("api/patients")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@GetMapping("/{cpf}")
	public PatientDTO findByCpf(@PathVariable @NotBlank String cpf) {
		return this.patientService.findByCpf(cpf);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public PatientDTO save(@RequestBody @Valid PatientInput patientInput) {
		return this.patientService.save(patientInput);
	}

	@DeleteMapping("/{cpf}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable @NotBlank String cpf) {
		patientService.delete(cpf);
	}

	@GetMapping
    public ResponseEntity<PageWrapper<Patient>> getAllWithPaginate(PatientFilter patientFilter, @PageableDefault(size = 5) Pageable pageable) {
		return ResponseEntity.ok(patientService.getAllWithPaginate(patientFilter, pageable));
    }

	@PutMapping("{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public PatientDTO update(@RequestBody @Valid PatientInput patientInput, @PathVariable @Valid Long id) {
		return this.patientService.update(patientInput, id);
	}

}
