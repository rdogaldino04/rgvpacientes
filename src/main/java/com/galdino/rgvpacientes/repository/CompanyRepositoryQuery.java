package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.dto.company.CompanyDTO;
import com.galdino.rgvpacientes.dto.company.CompanyFilter;

import java.util.List;

public interface CompanyRepositoryQuery {

    List<CompanyDTO> getAll(CompanyFilter companyFilter);
}
