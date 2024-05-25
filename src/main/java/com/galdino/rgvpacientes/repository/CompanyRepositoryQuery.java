package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.dto.company.CompanyDTO;
import com.galdino.rgvpacientes.dto.company.CompanyFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyRepositoryQuery {

    Page<CompanyDTO> getAll(CompanyFilter companyFilter, Pageable pageable);
}
