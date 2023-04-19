package com.galdino.rgvpacientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galdino.rgvpacientes.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer>, MenuRepositoryQuery {

}
