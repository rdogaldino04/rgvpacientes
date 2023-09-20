package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.dto.material.MaterialDTO;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class MaterialRepositoryImpl implements MaterialRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<MaterialDTO> getAll(MaterialDTO materialDTO) {
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

        return createQuery.getResultList();
    }

}
