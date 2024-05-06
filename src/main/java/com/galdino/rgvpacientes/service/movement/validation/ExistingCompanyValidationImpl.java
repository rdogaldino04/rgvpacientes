package com.galdino.rgvpacientes.service.movement.validation;

import com.galdino.rgvpacientes.model.Movement;
import com.galdino.rgvpacientes.service.CompanyService;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class ExistingCompanyValidationImpl implements  MovementValidationStrategy {

    private final CompanyService companyService;

    public ExistingCompanyValidationImpl(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public void execute(Movement movement) {
        validateCompany(movement);
    }

    private void validateCompany(Movement movement) {
        if (!this.companyService.existsById(movement.getCompany().getId())) {
            throw new EntityNotFoundException(String.format("There is no company with id %d", movement.getCompany().getId()));
        }
    }

}
