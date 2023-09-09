package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.model.Material;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class MaterialRepositoryImpl implements MaterialRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Material> getAll(String name) {
        StringBuilder sql = new StringBuilder("SELECT ");
        sql.append("m ");
        sql.append("FROM Material m ");
        sql.append("WHERE 1 = 1 ");

        if (StringUtils.hasText(name)) {
            sql.append("AND UPPER(m.name) LIKE UPPER(:name) ");
        }

        sql.append("ORDER BY m.name ");

        TypedQuery<Material> createQuery = manager.createQuery(sql.toString(), Material.class)
                .setMaxResults(180);

        if (StringUtils.hasText(name)) {
            createQuery.setParameter("name", name.concat("%"));
        }

        return createQuery.getResultList();
    }

}
