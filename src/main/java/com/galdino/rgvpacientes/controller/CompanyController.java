package com.galdino.rgvpacientes.controller;

import com.galdino.rgvpacientes.dto.CompanyDTO;
import com.galdino.rgvpacientes.dto.CompanyFilter;
import com.galdino.rgvpacientes.model.Company;
import com.galdino.rgvpacientes.service.CompanyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyDTO> findAll(CompanyFilter companyFilter) {
        return this.companyService.getAll(companyFilter);
    }

    @GetMapping("cnpj/{cnpj}")
    public Company findByCnpj(@PathVariable String cnpj) {
        return companyService.findByCnpj(cnpj);
    }

}
