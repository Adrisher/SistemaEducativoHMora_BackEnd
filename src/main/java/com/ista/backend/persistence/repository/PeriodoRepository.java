package com.ista.backend.persistence.repository;

import com.ista.backend.persistence.entity.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodoRepository extends JpaRepository<Periodo,Long> {

}
