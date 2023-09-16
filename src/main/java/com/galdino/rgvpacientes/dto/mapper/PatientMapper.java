package com.galdino.rgvpacientes.dto.mapper;

import org.springframework.stereotype.Component;

import com.galdino.rgvpacientes.dto.AddressOut;
import com.galdino.rgvpacientes.dto.PatientInput;
import com.galdino.rgvpacientes.dto.PatientDTO;
import com.galdino.rgvpacientes.enums.Status;
import com.galdino.rgvpacientes.model.Address;
import com.galdino.rgvpacientes.model.Patient;

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

}
