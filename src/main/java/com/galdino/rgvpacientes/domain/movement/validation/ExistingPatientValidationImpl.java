package com.galdino.rgvpacientes.domain.movement.validation;

import com.galdino.rgvpacientes.domain.movement.model.Movement;
import com.galdino.rgvpacientes.domain.patient.service.PatientService;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class ExistingPatientValidationImpl implements  MovementValidationStrategy {

    private final PatientService patientService;

    public ExistingPatientValidationImpl(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public void execute(Movement movement) {
        validatePatient(movement);
    }

    private void validatePatient(Movement movement) {
        if (!this.patientService.existsById(movement.getPatient().getId())) {
            throw new EntityNotFoundException(String.format("There is no patient with id %d", movement.getPatient().getId()));
        }
    }

}
