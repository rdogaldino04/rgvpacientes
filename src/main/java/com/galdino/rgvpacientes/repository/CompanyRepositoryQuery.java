package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.dto.CompanyDTO;
import com.galdino.rgvpacientes.dto.CompanyFilter;
import com.galdino.rgvpacientes.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepositoryQuery {

    List<CompanyDTO> getAll(CompanyFilter companyFilter);
}
