package com.galdino.rgvpacientes.domain.company.controller;

import com.galdino.rgvpacientes.domain.company.dto.CompanyDTO;
import com.galdino.rgvpacientes.domain.company.dto.CompanyFilter;
import com.galdino.rgvpacientes.domain.company.dto.CompanySaveDTO;
import com.galdino.rgvpacientes.domain.company.mapper.CompanyMapper;
import com.galdino.rgvpacientes.domain.company.model.Company;
import com.galdino.rgvpacientes.domain.company.service.CompanyService;
import com.galdino.rgvpacientes.shared.util.page.PageWrapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @GetMapping
    public PageWrapper<CompanyDTO> getAll(CompanyFilter companyFilter, @PageableDefault(size = 5) Pageable pageable) {
        return this.companyService.getAll(companyFilter, pageable);
    }

    @GetMapping("cnpj/{cnpj}")
    public Company findByCnpj(@PathVariable String cnpj) {
        return companyService.findByCnpj(cnpj);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyDTO save(@RequestBody @Valid CompanySaveDTO companySaveDTO) {
        Company company = this.companyMapper.toEntity(companySaveDTO);
        return this.companyMapper.toDTO(this.companyService.save(company));
    }

    @PutMapping("{id}")
    public CompanyDTO update(@PathVariable Long id, @RequestBody @Valid CompanySaveDTO companySaveDTO) {
        Company company = this.companyMapper.toEntity(companySaveDTO);
        company.setId(id);
        return this.companyMapper.toDTO(this.companyService.update(company));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.companyService.delete(id);
    }

    @GetMapping("{id}")
    public CompanyDTO findById(@PathVariable Long id) {
        return this.companyMapper.toDTO(this.companyService.findById(id));
    }

}
