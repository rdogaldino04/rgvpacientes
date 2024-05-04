package com.galdino.rgvpacientes.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.galdino.rgvpacientes.dto.product.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.galdino.rgvpacientes.dto.product.ProductDTO;

public class ProductRepositoryImpl implements ProductRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<ProductDTO> getProductByFilter(ProductFilter productFilter, Pageable pageable) {
        Map<String, Object> parameters = new HashMap<>();
        StringBuilder sql = new StringBuilder("SELECT ");
        sql.append("new com.galdino.rgvpacientes.dto.product.ProductDTO( ");
        sql.append("  m.id, m.name, m.expirationDate, m.registrationDate ");
        sql.append(" ) ");
        sql.append("FROM Product m ");
        sql.append("WHERE 1 = 1 ");

        if (productFilter.getId() != null) {
            sql.append("AND m.id = :id ");
            parameters.put("id", productFilter.getId());
        }

        if (StringUtils.hasText(productFilter.getName())) {
            sql.append("AND UPPER(m.name) LIKE UPPER(:name) ");
            parameters.put("name", productFilter.getName().concat("%"));
        }

        sql.append("ORDER BY m.name ");

        TypedQuery<ProductDTO> createQuery = manager.createQuery(sql.toString(), ProductDTO.class)
                .setMaxResults(180);

        parameters.keySet().forEach(key -> createQuery.setParameter(key, parameters.get(key)));

        createQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        createQuery.setMaxResults(pageable.getPageSize());

        List<ProductDTO> results = createQuery.getResultList();

        long totalElements = getTotalElements(productFilter, parameters);

        return new PageImpl<>(results, pageable, totalElements);
    }

    private long getTotalElements(ProductFilter productFilter, Map<String, Object> parameters) {
        StringBuilder countSql = new StringBuilder("SELECT COUNT(m.id) FROM Product m WHERE 1 = 1 ");
        if (productFilter.getId() != null) {
            countSql.append("AND m.id = :id ");
            parameters.put("id", productFilter.getId());
        }
        if (StringUtils.hasText(productFilter.getName())) {
            countSql.append("AND UPPER(m.name) LIKE UPPER(:name) ");
            parameters.put("name", productFilter.getName().concat("%"));
        }
        TypedQuery<Long> countQuery = manager.createQuery(countSql.toString(), Long.class);
        parameters.keySet().forEach(key -> countQuery.setParameter(key, parameters.get(key)));
        return countQuery.getSingleResult();
    }

}
