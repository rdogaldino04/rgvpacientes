package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.dto.sector.SectorFilter;
import com.galdino.rgvpacientes.dto.stock.StockDTO;
import com.galdino.rgvpacientes.model.Sector;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class SectorRepositoryImpl implements SectorRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Sector> getAll(SectorFilter sectorFilter) {
        StringBuilder sql = new StringBuilder("SELECT ");
        sql.append("DISTINCT s ");
        sql.append("FROM Sector s ");
        sql.append("JOIN FETCH s.stocks ");
        sql.append("LEFT JOIN FETCH s.company ");
        sql.append("WHERE 1 = 1 ");

        if (sectorFilter.getId() != null) {
            sql.append("AND s.id = :id ");
        }

        if (StringUtils.hasText(sectorFilter.getName())) {
            sql.append("AND UPPER(s.name) LIKE UPPER(:name) ");
        }

        if (sectorFilter.getCompanyId() != null) {
            sql.append("AND s.company.id = :companyId ");
        }

        sql.append("ORDER BY s.name ");

        TypedQuery<Sector> createQuery = manager.createQuery(sql.toString(), Sector.class)
                .setMaxResults(180);

        if (sectorFilter.getId() != null) {
            createQuery.setParameter("id", sectorFilter.getId());
        }

        if (StringUtils.hasText(sectorFilter.getName())) {
            createQuery.setParameter("name", sectorFilter.getName().concat("%"));
        }

        if (sectorFilter.getCompanyId() != null) {
            createQuery.setParameter("companyId", sectorFilter.getCompanyId());
        }

        return createQuery.getResultList();
    }

    @Override
    public List<StockDTO> stocksFindBySector(SectorFilter sectorFilter) {
        StringBuilder sql = new StringBuilder("SELECT ");
        sql.append("new com.galdino.rgvpacientes.dto.stock.StockDTO(st.id, st.name) ");
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

}
