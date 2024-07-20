package com.galdino.rgvpacientes.domain.menu.repository;

import com.galdino.rgvpacientes.domain.menu.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer>, MenuRepositoryQuery {

}
