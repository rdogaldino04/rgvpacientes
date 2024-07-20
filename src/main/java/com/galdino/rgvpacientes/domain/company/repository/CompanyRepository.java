package com.galdino.rgvpacientes.domain.company.repository;

import java.util.Optional;

import com.galdino.rgvpacientes.domain.company.model.Company;
import com.galdino.rgvpacientes.shared.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends CustomJpaRepository<Company, Long>, CompanyRepositoryQuery {

    Optional<Company> findByCnpj(String cnpj);

    @Query("select case when count(c) > 0 then true else false end from Sector c where c.company.id = :id")
    boolean existsBySector(Long id);

}
