package com.galdino.rgvpacientes.repository;

import com.galdino.rgvpacientes.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorRepository extends JpaRepository<Sector, Long>, SectorRepositoryQuery {

    boolean existsByCompanyId(Long companyId);
}
