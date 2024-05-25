package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.dto.company.CompanyDTO;
import com.galdino.rgvpacientes.dto.company.CompanyFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyRepositoryImpl implements CompanyRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<CompanyDTO> getAll(CompanyFilter companyFilter, Pageable pageable) {
        Map<String, Object> parameters = new HashMap<>();
        String sqlFields = "SELECT " +
                "new com.galdino.rgvpacientes.dto.company.CompanyDTO(c.id, c.name, c.cnpj) ";
        String sqlFrom = "FROM Company c WHERE 1 = 1 ";
        String sqlWhere = "";
        if (StringUtils.hasText(companyFilter.getName())) {
            sqlWhere = sqlWhere + "AND UPPER(c.name) LIKE UPPER(:name) ";
            parameters.put("name", companyFilter.getName().concat("%"));
        }
        if (companyFilter.getCnpj() != null) {
            sqlWhere = sqlWhere + "AND c.cnpj = :cnpj ";
            parameters.put("cnpj", companyFilter.getCnpj());
        }
        String sqlOrder = "ORDER BY c.name ";
        String sql = sqlFields + sqlFrom + sqlWhere + sqlOrder;
        TypedQuery<CompanyDTO> createQuery = manager.createQuery(sql, CompanyDTO.class)
                .setMaxResults(180);

        parameters.keySet().forEach(key -> createQuery.setParameter(key, parameters.get(key)));

        createQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        createQuery.setMaxResults(pageable.getPageSize());

        List<CompanyDTO> results = createQuery.getResultList();
        long totalElements = getTotalElements(sqlWhere, parameters);
        return new PageImpl<>(results, pageable, totalElements);
    }

    private long getTotalElements(String sqlWhere, Map<String, Object> parameters) {
        String sqlCount = "SELECT COUNT(c) FROM Company c WHERE 1 = 1 ";
        sqlCount = sqlCount + sqlWhere;
        TypedQuery<Long> createQueryCount = manager.createQuery(sqlCount, Long.class);
        parameters.keySet().forEach(key -> createQueryCount.setParameter(key, parameters.get(key)));
        return createQueryCount.getSingleResult();
    }

}
