package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.dto.stock.StockFilter;
import com.galdino.rgvpacientes.model.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Map;

public class StockRepositoryImpl implements StockRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Stock> getStockByFilter(StockFilter stockFilter, Pageable pageable) {
        Map<String, Object> parameters = new HashMap<>();
        String sql = "SELECT s FROM Stock s JOIN FETCH s.sector sec JOIN FETCH sec.company comp WHERE 1 = 1 ";

        if (stockFilter.getId() != null) {
            sql += "AND s.id = :id ";
            parameters.put("id", stockFilter.getId());
        }

        if (stockFilter.getSectorId() != null) {
            sql += "AND sec.id = :sectorId ";
            parameters.put("sectorId", stockFilter.getSectorId());
        }

        if (StringUtils.hasText(stockFilter.getName())) {
            sql += "AND UPPER(s.name) LIKE UPPER(:name) ";
            parameters.put("name", stockFilter.getName().concat("%"));
        }

        sql += "ORDER BY s.name ";

        TypedQuery<Stock> createQuery = manager.createQuery(sql, Stock.class)
                .setMaxResults(180);
        parameters.keySet().forEach(key -> createQuery.setParameter(key, parameters.get(key)));

        createQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        createQuery.setMaxResults(pageable.getPageSize());

        long totalElements = getTotalElements(stockFilter, parameters);
        return new PageImpl<>(createQuery.getResultList(), pageable, totalElements);
    }

    private long getTotalElements(StockFilter stockFilter, Map<String, Object> parameters) {
        String sql = "SELECT COUNT(s) FROM Stock s JOIN s.sector sec JOIN sec.company comp WHERE 1 = 1 ";

        if (stockFilter.getId() != null) {
            sql += "AND s.id = :id ";
        }

        if (stockFilter.getSectorId() != null) {
            sql += "AND sec.id = :sectorId ";
        }

        if (StringUtils.hasText(stockFilter.getName())) {
            sql += "AND UPPER(s.name) LIKE UPPER(:name) ";
        }

        TypedQuery<Long> createQuery = manager.createQuery(sql, Long.class);
        parameters.keySet().forEach(key -> createQuery.setParameter(key, parameters.get(key)));

        return createQuery.getSingleResult();
    }

}
