package com.galdino.rgvpacientes.repository.specs;

import com.galdino.rgvpacientes.batch.dto.BatchFilter;
import com.galdino.rgvpacientes.batch.model.Batch;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class BatchSpecs {

    private BatchSpecs() {
    }

    public static Specification<Batch> usingFilter(BatchFilter filter) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getId() != null) {
                predicates.add(builder.equal(root.get("id"), filter.getId()));
            }
            if (StringUtils.hasText(filter.getBatchNumber())) {
                predicates.add(builder.like(root.get("batchNumber"), filter.getBatchNumber().toUpperCase().concat("%")));
            }
            if (filter.getProductId() != null) {
                predicates.add(builder.equal(root.get("product").get("id"), filter.getProductId()));
            }
            query.orderBy(builder.asc(root.get("batchNumber")));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
