package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.dto.CompanyDTO;
import com.galdino.rgvpacientes.dto.CompanyFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class CompanyRepositoryImpl implements CompanyRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<CompanyDTO> getAll(CompanyFilter companyFilter) {
        StringBuilder sql = new StringBuilder("SELECT ");
        sql.append("new com.galdino.rgvpacientes.dto.CompanyDTO(c.id, c.name, c.cnpj) ");
        sql.append("FROM Company c ");
        sql.append("WHERE ( UPPER(c.name) LIKE UPPER(:name) OR c.name IS NULL) ");
        sql.append("ORDER BY c.name ");

        TypedQuery<CompanyDTO> createQuery = manager.createQuery(sql.toString(), CompanyDTO.class)
                .setParameter("name", companyFilter.getName().concat("%"))
                .setMaxResults(180);
        return createQuery.getResultList();
    }

}
