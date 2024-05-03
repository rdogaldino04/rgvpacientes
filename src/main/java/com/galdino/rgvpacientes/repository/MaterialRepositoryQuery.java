package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.dto.material.MaterialFilter;
import org.springframework.data.domain.Page;

import com.galdino.rgvpacientes.dto.material.MaterialDTO;
import org.springframework.data.domain.Pageable;

public interface MaterialRepositoryQuery {

    Page<MaterialDTO> getMaterialsByFilter(MaterialFilter materialFilter, Pageable pageable);

}
