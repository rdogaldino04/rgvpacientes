package com.galdino.rgvpacientes.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.galdino.rgvpacientes.dto.material.MaterialDTO;

public class MaterialRepositoryImpl implements MaterialRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<MaterialDTO> getAll(MaterialDTO materialDTO, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT ");
        sql.append("new com.galdino.rgvpacientes.dto.material.MaterialDTO( ");
        sql.append("  m.id, m.name, m.expirationDate, m.registrationDate ");
        sql.append(" ) ");
        sql.append("FROM Material m ");
        sql.append("WHERE 1 = 1 ");

        if (materialDTO.getId() != null) {
            sql.append("AND m.id = :id ");
        }

        if (StringUtils.hasText(materialDTO.getName())) {
            sql.append("AND UPPER(m.name) LIKE UPPER(:name) ");
        }

        sql.append("ORDER BY m.name ");

        TypedQuery<MaterialDTO> createQuery = manager.createQuery(sql.toString(), MaterialDTO.class)
                .setMaxResults(180);

        if (materialDTO.getId() != null) {
            createQuery.setParameter("id", materialDTO.getId());
        }

        if (StringUtils.hasText(materialDTO.getName())) {
            createQuery.setParameter("name", materialDTO.getName().concat("%"));
        }

        createQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        createQuery.setMaxResults(pageable.getPageSize());

        List<MaterialDTO> results = createQuery.getResultList();

        TypedQuery<Long> countQuery = manager.createQuery("SELECT COUNT(m.id) FROM Material m", Long.class);
        long totalElements = countQuery.getSingleResult();

        return new PageImpl<>(results, pageable, totalElements);
    }

}
