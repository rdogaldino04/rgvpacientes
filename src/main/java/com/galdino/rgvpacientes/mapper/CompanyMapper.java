package com.galdino.rgvpacientes.mapper;

import com.galdino.rgvpacientes.dto.company.CompanyDTO;
import com.galdino.rgvpacientes.dto.company.CompanySaveDTO;
import com.galdino.rgvpacientes.model.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyDTO toDTO(Company company) {
        return CompanyDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .cnpj(company.getCnpj())
                .build();
    }

    public Company toEntity(CompanyDTO companyDTO) {
        Company company = new Company();
        company.setName(companyDTO.getName());
        company.setCnpj(companyDTO.getCnpj());
        return company;
    }

    public Company toEntity(CompanySaveDTO companySaveDTO) {
        Company company = new Company();
        company.setName(companySaveDTO.getName());
        company.setCnpj(companySaveDTO.getCnpj());
        return company;
    }

    public Company toCompany(Long companyId) {
        Company company = new Company();
        company.setId(companyId);
        return company;
    }
}
