package com.galdino.rgvpacientes.service;

import com.galdino.rgvpacientes.dto.company.CompanyDTO;
import com.galdino.rgvpacientes.dto.company.CompanyFilter;
import com.galdino.rgvpacientes.exception.BusinessException;
import com.galdino.rgvpacientes.model.Company;
import com.galdino.rgvpacientes.repository.CompanyRepository;
import com.galdino.rgvpacientes.util.page.PageWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public PageWrapper<CompanyDTO> getAll(CompanyFilter companyFilter, Pageable pageable) {
        Page<CompanyDTO> page = this.companyRepository.getAll(companyFilter, pageable);
        return new PageWrapper<>(page);
    }

    public Company findByCnpj(String cnpj) {
        return companyRepository.findByCnpj(cnpj)
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format("There is no company with cnpj %s", cnpj)));
    }

    public boolean existsById(Long companyId) {
        return this.companyRepository.existsById(companyId);
    }

    @Transactional
    public Company save(Company company) {
        findByCnpj(company);
        return this.companyRepository.save(company);
    }

    private void findByCnpj(Company company) {
        this.companyRepository.findByCnpj(company.getCnpj())
                .ifPresent(c -> {
                    if (!c.getId().equals(company.getId())) {
                        throw new BusinessException(
                                String.format("There is already a company with cnpj %s", company.getCnpj()));
                    }
                });
    }

    @Transactional
    public Company update(Company company) {
        Company companyCurrent = this.findById(company.getId());
        if (!companyCurrent.getCnpj().equals(company.getCnpj())) {
            findByCnpj(company);
        }
        BeanUtils.copyProperties(company, companyCurrent, "id");
        return companyCurrent;
    }

    public Company findById(Long id) {
        return this.companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("There is no company with id %s", id)));
    }
}
