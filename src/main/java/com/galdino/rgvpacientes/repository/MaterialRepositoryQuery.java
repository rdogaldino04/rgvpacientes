package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.dto.material.MaterialDTO;

import java.util.List;

public interface MaterialRepositoryQuery {

    List<MaterialDTO> getAll(MaterialDTO materialDTO);

}
