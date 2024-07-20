package com.galdino.rgvpacientes.domain.sector.repository;

import com.galdino.rgvpacientes.domain.sector.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorRepository extends JpaRepository<Sector, Long>, SectorRepositoryQuery {

    boolean existsByCompanyId(Long companyId);
}
