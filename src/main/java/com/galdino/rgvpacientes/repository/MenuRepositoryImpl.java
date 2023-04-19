package com.galdino.rgvpacientes.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.galdino.rgvpacientes.model.Menu;

public class MenuRepositoryImpl implements MenuRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Menu> getMenus() {
        TypedQuery<Menu> query = manager.createQuery(
                "select distinct m " +
                        "from Menu m " +
                        "left join fetch m.subMenus " +
                        "where m.active = 'true' " +
                        "and m.menuParent.id is null " +
                        "order by m.id ",
                Menu.class);
        return query.getResultList();
    }

}
