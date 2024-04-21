package com.galdino.rgvpacientes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.galdino.rgvpacientes.dto.material.MaterialDTO;

public interface MaterialRepositoryQuery {

    Page<MaterialDTO> getMaterialsByFilter(MaterialDTO materialDTO, Pageable pageable);

}
