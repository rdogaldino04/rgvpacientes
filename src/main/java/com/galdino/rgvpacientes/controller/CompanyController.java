package com.galdino.rgvpacientes.controller;

import com.galdino.rgvpacientes.dto.company.CompanyDTO;
import com.galdino.rgvpacientes.dto.company.CompanyFilter;
import com.galdino.rgvpacientes.dto.company.CompanySaveDTO;
import com.galdino.rgvpacientes.mapper.CompanyMapper;
import com.galdino.rgvpacientes.model.Company;
import com.galdino.rgvpacientes.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public List<CompanyDTO> findAll(CompanyFilter companyFilter) {
        return this.companyService.getAll(companyFilter);
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

}
