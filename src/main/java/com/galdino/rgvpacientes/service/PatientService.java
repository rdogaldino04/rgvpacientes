package com.galdino.rgvpacientes.service;

import com.galdino.rgvpacientes.dto.patient.PatientDTO;
import com.galdino.rgvpacientes.dto.patient.PatientFilter;
import com.galdino.rgvpacientes.dto.patient.PatientInput;
import com.galdino.rgvpacientes.exception.BusinessException;
import com.galdino.rgvpacientes.mapper.PatientMapper;
import com.galdino.rgvpacientes.model.Patient;
import com.galdino.rgvpacientes.repository.PatientRepository;
import com.galdino.rgvpacientes.repository.specs.PatientSpecs;
import com.galdino.rgvpacientes.shared.util.page.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patienteRepository;

    @Autowired
    private PatientMapper patientMapper;

    public PatientDTO findByCpf(String cpf) {
        return this.patientMapper.toDTO(this.patienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException(cpf)));
    }

    @Transactional(rollbackFor = Exception.class)
    public PatientDTO save(@Valid PatientInput patientInput) {
        patientInput.setId(null);
        if (patienteRepository.existsByCpf(patientInput.getCpf())) {
            throw new BusinessException("Já existe um paciente com cpf " + patientInput.getCpf() +
                    " desativado, revise as informações ou desative o paciente.");
        }
        Patient patient = patienteRepository.save(patientMapper.toModel(patientInput));
        return patientMapper.toDTO(patient);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(@NotBlank String cpf) {
        patienteRepository.delete(
                patienteRepository.findByCpf(cpf)
                        .orElseThrow(() -> new EntityNotFoundException(cpf)));
    }

    public PageWrapper<Patient> getAllWithPaginate(PatientFilter patientFilter, Pageable pageable) {
        Page<Patient> patientPage = this.patienteRepository.findAll(PatientSpecs.usingFilter(patientFilter), pageable);
        return new PageWrapper<>(patientPage);
    }

    @Transactional(rollbackFor = Exception.class)
    public PatientDTO update(@Valid PatientInput patientInput, @Valid Long id) {
        patientInput.setId(id);
        Patient patientCurrent = findById(id);
        patienteRepository.detach(patientCurrent);
        patientMapper.copyToDomainObject(patientInput, patientCurrent);
        Optional<Patient> patientExisting = patienteRepository.findByCpf(patientInput.getCpf());
        boolean exist = patientExisting.isPresent() && !patientExisting.get().equals(patientCurrent);
        if (exist) {
            throw new BusinessException(
                    String.format("There is already a registered patient with the cpf %s", patientInput.getCpf())
            );
        }
        return patientMapper.toDTO(patienteRepository.save(patientCurrent));
    }

    public Patient findById(Long id) {
        return patienteRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no patient with code %d", id)));
    }

    public boolean existsById(Long id) {
        return patienteRepository.existsById(id);
    }

}
