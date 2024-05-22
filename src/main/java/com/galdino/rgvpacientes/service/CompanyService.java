package com.galdino.rgvpacientes.service;

import com.galdino.rgvpacientes.dto.company.CompanyDTO;
import com.galdino.rgvpacientes.dto.company.CompanyFilter;
import com.galdino.rgvpacientes.model.Company;
import com.galdino.rgvpacientes.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<CompanyDTO> getAll(CompanyFilter companyFilter) {
        if (companyFilter != null && companyFilter.getName() == null) {
            companyFilter.setName("");
        }
        return this.companyRepository.getAll(companyFilter);
    }

    public Company findByCnpj(String cnpj) {
        return companyRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no company with cnpj %s", cnpj)));
    }

    public boolean existsById(Long companyId) {
        return this.companyRepository.existsById(companyId);
    }
}
