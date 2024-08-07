package com.galdino.rgvpacientes.domain.movement.repository;

import com.galdino.rgvpacientes.domain.movement.dto.MovementFilter;
import com.galdino.rgvpacientes.domain.movement.model.Movement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovementRepositoryImpl implements MovementRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Movement> getByFilter(MovementFilter movementFilter, Pageable pageable) {
        Map<String, Object> parameters = new HashMap<>();

        String sqlFields = "SELECT DISTINCT m ";

        String sqlFrom = "FROM Movement m " +
                "JOIN m.patient p " +
                "JOIN m.stockSourceLocation st " +
                "LEFT JOIN m.stockDestinationLocation sd " +
                "JOIN st.sector s " +
                "JOIN s.company c ";

        String sqlWhere = "WHERE 1 = 1 ";

        if (movementFilter.getId() != null) {
            sqlWhere = sqlWhere + "AND m.id = :id ";
            parameters.put("id", movementFilter.getId());
        }

        if (movementFilter.getPatientId() != null) {
            sqlWhere = sqlWhere + "AND m.patient.id = :patientId ";
            parameters.put("patientId", movementFilter.getPatientId());
        }

        if (movementFilter.getCompanyId() != null) {
            sqlWhere = sqlWhere + "AND m.company.id = :companyId ";
            parameters.put("companyId", movementFilter.getCompanyId());
        }

        if (movementFilter.getSectorId() != null) {
            sqlWhere = sqlWhere + "AND m.sector.id = :sectorId ";
            parameters.put("sectorId", movementFilter.getSectorId());
        }

        if (movementFilter.getStockId() != null) {
            sqlWhere = sqlWhere + "AND m.stock.id = :stockId ";
            parameters.put("stockId", movementFilter.getStockId());
        }

        String sqlOrder = "ORDER BY m.id ";
        String sql = sqlFields + sqlFrom + sqlWhere + sqlOrder;

        TypedQuery<Movement> createQuery = manager.createQuery(sql, Movement.class)
                .setMaxResults(180);

        parameters.keySet().forEach(key -> createQuery.setParameter(key, parameters.get(key)));

        createQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        createQuery.setMaxResults(pageable.getPageSize());

        List<Movement> results = createQuery.getResultList();

        long totalElements = getTotalElements(sqlWhere, parameters);

        return new PageImpl<>(results, pageable, totalElements);

    }

    private long getTotalElements(String sqlWhere, Map<String, Object> parameters) {
        String sqlCount = "SELECT COUNT(m) " +
                "FROM Movement m " +
                "JOIN m.patient p " +
                "JOIN m.stockSourceLocation st " +
                "LEFT JOIN m.stockDestinationLocation sd " +
                "JOIN st.sector s " +
                "JOIN s.company c ";
        sqlCount = sqlCount + sqlWhere;
        TypedQuery<Long> createQuery = manager.createQuery(sqlCount, Long.class);
        parameters.keySet().forEach(key -> createQuery.setParameter(key, parameters.get(key)));
        return createQuery.getSingleResult();
    }
}
