package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.model.Material;

import java.util.List;

public interface MaterialRepositoryQuery {

    List<Material> getAll(String name);

}
