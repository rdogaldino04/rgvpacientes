package com.galdino.rgvpacientes.domain.sector.repository;

import com.galdino.rgvpacientes.domain.sector.dto.SectorDTO;
import com.galdino.rgvpacientes.domain.sector.dto.SectorFilter;
import com.galdino.rgvpacientes.domain.stock.dto.StockDTO;
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

public class SectorRepositoryImpl implements SectorRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<SectorDTO> getAll(SectorFilter sectorFilter, Pageable pageable) {
        Map<String, Object> parameters = new HashMap<>();
        String sqlFields = "SELECT " +
                "DISTINCT new com.galdino.rgvpacientes.sector.sector.SectorDTO(" +
                "s.id, s.name, s.company.id, s.company.name) ";
        String sqlFrom = "FROM Sector s " +
                "JOIN s.company ";
        String sqlWhere = "WHERE 1 = 1 ";

        if (sectorFilter.getId() != null) {
            sqlWhere = sqlWhere + "AND s.id = :id ";
            parameters.put("id", sectorFilter.getId());
        }

        if (StringUtils.hasText(sectorFilter.getName())) {
            sqlWhere = sqlWhere + "AND UPPER(s.name) LIKE UPPER(:name) ";
            parameters.put("name", sectorFilter.getName().concat("%"));
        }

        if (sectorFilter.getCompanyId() != null) {
            sqlWhere = sqlWhere + "AND s.company.id = :companyId ";
            parameters.put("companyId", sectorFilter.getCompanyId());
        }

        String sqlOrder = "ORDER BY s.name ";
        String sql = sqlFields + sqlFrom + sqlWhere + sqlOrder;

        TypedQuery<SectorDTO> createQuery = manager.createQuery(sql, SectorDTO.class)
                .setMaxResults(180);
        parameters.keySet().forEach(key -> createQuery.setParameter(key, parameters.get(key)));

        createQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        createQuery.setMaxResults(pageable.getPageSize());

        List<SectorDTO> results = createQuery.getResultList();
        long totalElements = getTotalElements(sqlWhere, parameters);
        return new PageImpl<>(results, pageable, totalElements);
    }

    @Override
    public List<StockDTO> stocksFindBySector(SectorFilter sectorFilter) {
        StringBuilder sql = new StringBuilder("SELECT ");
        sql.append("new com.galdino.rgvpacientes.stock.stock.StockDTO(st.id, st.name) ");
        sql.append("FROM Stock st ");
        sql.append("JOIN Sector s ON s.id = st.sector.id ");
        sql.append("WHERE st.sector.id = :sectorId ");

        if (StringUtils.hasText(sectorFilter.getStockName())) {
            sql.append("AND UPPER(st.name) LIKE UPPER(:stockName) ");
        }

        if (sectorFilter.getStockId() != null) {
            sql.append("AND st.id = :stockId ");
        }

        sql.append("ORDER BY st.name ASC");

        TypedQuery<StockDTO> createQuery = manager.createQuery(sql.toString(), StockDTO.class)
                .setParameter("sectorId", sectorFilter.getId())
                .setMaxResults(180);

        if (StringUtils.hasText(sectorFilter.getStockName())) {
            createQuery.setParameter("stockName", sectorFilter.getStockName().concat("%"));
        }

        if (sectorFilter.getStockId() != null) {
            createQuery.setParameter("stockId", sectorFilter.getStockId());
        }

        return createQuery.getResultList();
    }

    private long getTotalElements(String sqlWhere, Map<String, Object> parameters) {
        String sqlCount = "SELECT COUNT(s) FROM Sector s JOIN s.company ";
        sqlCount = sqlCount + sqlWhere;
        TypedQuery<Long> createQueryCount = manager.createQuery(sqlCount, Long.class);
        parameters.keySet().forEach(key -> createQueryCount.setParameter(key, parameters.get(key)));
        return createQueryCount.getSingleResult();
    }

}
