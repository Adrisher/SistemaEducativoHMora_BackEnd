package com.ista.backend.persistence.repository;

import com.ista.backend.persistence.entity.Materia;
import com.ista.backend.persistence.enums.MateriaStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MateriaRepository extends JpaRepository<Materia,Long> {

    public Optional<Materia> findByMateriaDetalle(String materiaStatus);
}
