package com.galdino.rgvpacientes.domain.patient.mapper;

import com.galdino.rgvpacientes.domain.address.dto.AddressOut;
import com.galdino.rgvpacientes.domain.patient.dto.PatientDTO;
import com.galdino.rgvpacientes.domain.patient.dto.PatientInput;
import com.galdino.rgvpacientes.domain.patient.dto.PatientMovementDTO;
import com.galdino.rgvpacientes.domain.patient.model.Patient;
import com.galdino.rgvpacientes.domain.status.enums.Status;
import com.galdino.rgvpacientes.domain.address.model.Address;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public Patient toModel(PatientInput patientInput) {
        Patient patient = new Patient();
        patient.setCpf(patientInput.getCpf());
        patient.setName(patientInput.getName());
        patient.setPhone(patientInput.getPhone());
        patient.setAddress(Address.builder()
                .addressName(patientInput.getAddress().getAddressName())
                .number(patientInput.getAddress().getNumber())
                .complement(patientInput.getAddress().getComplement())
                .district(patientInput.getAddress().getDistrict())
                .build());
        patient.setStatus(Status.ACTIVE);

        return patient;
    }

    public PatientDTO toDTO(Patient patient) {
        if (patient == null) {
            return null;
        }

        AddressOut addressOut = new AddressOut();
        if (patient.getAddress() != null) {
            addressOut.setAddressName(patient.getAddress().getAddressName());
            addressOut.setNumber(patient.getAddress().getNumber());
            addressOut.setComplement(patient.getAddress().getComplement());
            addressOut.setDistrict(patient.getAddress().getDistrict());
        }

        return new PatientDTO(
                patient.getId(),
                patient.getCpf(),
                patient.getName(),
                patient.getPhone(),
                addressOut);
    }

    public PatientMovementDTO toPatientMovementDTO(Patient patient) {
        return PatientMovementDTO.builder()
                .id(patient.getId())
                .name(patient.getName())
                .cpf(patient.getCpf())
                .build();
    }

    public void copyToDomainObject(PatientInput patientInput, Patient patient) {
        patient.setCpf(patientInput.getCpf());
        patient.setName(patientInput.getName());
        patient.setPhone(patientInput.getPhone());
        if (patientInput.getAddress() != null) {
            patient.getAddress().setAddressName(patientInput.getAddress().getAddressName());
            patient.getAddress().setNumber(patientInput.getAddress().getNumber());
            patient.getAddress().setComplement(patientInput.getAddress().getComplement());
            patient.getAddress().setDistrict(patientInput.getAddress().getDistrict());
        }
    }

    public Patient toPatient(Long patientId) {
        Patient patient = new Patient();
        patient.setId(patientId);
        return patient;
    }
}
