package com.galdino.rgvpacientes.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.galdino.rgvpacientes.dto.material.MaterialFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.galdino.rgvpacientes.dto.material.MaterialDTO;

public class MaterialRepositoryImpl implements MaterialRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<MaterialDTO> getMaterialsByFilter(MaterialFilter materialFilter, Pageable pageable) {
        Map<String, Object> parameters = new HashMap<>();
        StringBuilder sql = new StringBuilder("SELECT ");
        sql.append("new com.galdino.rgvpacientes.dto.material.MaterialDTO( ");
        sql.append("  m.id, m.name, m.expirationDate, m.registrationDate ");
        sql.append(" ) ");
        sql.append("FROM Material m ");
        sql.append("WHERE 1 = 1 ");

        if (materialFilter.getId() != null) {
            sql.append("AND m.id = :id ");
            parameters.put("id", materialFilter.getId());
        }

        if (StringUtils.hasText(materialFilter.getName())) {
            sql.append("AND UPPER(m.name) LIKE UPPER(:name) ");
            parameters.put("name", materialFilter.getName().concat("%"));
        }

        sql.append("ORDER BY m.name ");

        TypedQuery<MaterialDTO> createQuery = manager.createQuery(sql.toString(), MaterialDTO.class)
                .setMaxResults(180);

        parameters.keySet().forEach(key -> createQuery.setParameter(key, parameters.get(key)));

        createQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        createQuery.setMaxResults(pageable.getPageSize());

        List<MaterialDTO> results = createQuery.getResultList();

        long totalElements = getTotalElements(materialFilter, parameters);

        return new PageImpl<>(results, pageable, totalElements);
    }

    private long getTotalElements(MaterialFilter materialFilter, Map<String, Object> parameters) {
        StringBuilder countSql = new StringBuilder("SELECT COUNT(m.id) FROM Material m WHERE 1 = 1 ");
        if (materialFilter.getId() != null) {
            countSql.append("AND m.id = :id ");
            parameters.put("id", materialFilter.getId());
        }
        if (StringUtils.hasText(materialFilter.getName())) {
            countSql.append("AND UPPER(m.name) LIKE UPPER(:name) ");
            parameters.put("name", materialFilter.getName().concat("%"));
        }
        TypedQuery<Long> countQuery = manager.createQuery(countSql.toString(), Long.class);
        parameters.keySet().forEach(key -> countQuery.setParameter(key, parameters.get(key)));
        return countQuery.getSingleResult();
    }

}
