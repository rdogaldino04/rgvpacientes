package com.galdino.rgvpacientes.dto.mapper;

import com.galdino.rgvpacientes.dto.CompanyDTO;
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

}
