package com.galdino.rgvpacientes.repository;

import java.util.Optional;

import com.galdino.rgvpacientes.model.Company;

public interface CompanyRepository extends CustomJpaRepository<Company, Long>, CompanyRepositoryQuery {

    Optional<Company> findByCnpj(String cnpj);

}
