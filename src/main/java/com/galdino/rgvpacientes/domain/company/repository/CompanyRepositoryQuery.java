package com.galdino.rgvpacientes.domain.company.repository;

import com.galdino.rgvpacientes.domain.company.dto.CompanyDTO;
import com.galdino.rgvpacientes.domain.company.dto.CompanyFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyRepositoryQuery {

    Page<CompanyDTO> getAll(CompanyFilter companyFilter, Pageable pageable);
}
