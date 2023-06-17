package com.galdino.rgvpacientes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.galdino.rgvpacientes.dto.PatientFilter;
import com.galdino.rgvpacientes.model.Patient;

public interface PatientRepositoryQuery {
    
    Page<Patient> getAllWithPaginate(PatientFilter patientFilter, Pageable pageable);
    
}
