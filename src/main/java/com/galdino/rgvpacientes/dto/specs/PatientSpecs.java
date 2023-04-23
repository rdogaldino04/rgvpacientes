package com.galdino.rgvpacientes.dto.specs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.galdino.rgvpacientes.dto.PatientFilter;
import com.galdino.rgvpacientes.model.Patient;

public class PatientSpecs {

    private PatientSpecs() {
    }

    public static Specification<Patient> usingFilter(PatientFilter filter) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(filter.getCpf())) {
                predicates.add(builder.equal(root.get("cpf"), filter.getCpf()));
            }
            if (StringUtils.hasText(filter.getName())) {
                predicates.add(builder.like(root.get("name"), filter.getName().concat("%")));
            }
            query.orderBy(builder.asc(root.get("name")));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
