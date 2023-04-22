package com.galdino.rgvpacientes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.galdino.rgvpacientes.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

    @Query("select p from Patient p where p.status = 'Active' and p.cpf = :cpf")
    Optional<Patient> findById(String cpf);

}
