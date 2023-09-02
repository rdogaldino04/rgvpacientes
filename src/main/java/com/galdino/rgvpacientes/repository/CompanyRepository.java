package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long>, CompanyRepositoryQuery {

    Optional<Company> findByCnpj(String cnpj);

}
