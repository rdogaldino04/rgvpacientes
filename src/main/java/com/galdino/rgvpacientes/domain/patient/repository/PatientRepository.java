package com.galdino.rgvpacientes.domain.patient.repository;

import com.galdino.rgvpacientes.domain.patient.model.Patient;
import com.galdino.rgvpacientes.shared.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PatientRepository extends CustomJpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {

    @Query("select p from Patient p where p.status = 'Active' and p.cpf = :cpf")
    Optional<Patient> findByCpf(String cpf);

    boolean existsByCpf(String cpf);

}
