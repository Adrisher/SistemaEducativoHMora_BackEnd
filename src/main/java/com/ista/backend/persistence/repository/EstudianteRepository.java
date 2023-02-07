package com.ista.backend.persistence.repository;

import com.ista.backend.persistence.entity.Estudiante;
import com.ista.backend.persistence.enums.SexoStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante,Long> {

    public List<Estudiante> findAllByGenero(SexoStatus genero);

    public Optional<Estudiante> findByCedula(String cedula);
}
