package com.ista.backend.persistence.repository;

import com.ista.backend.persistence.entity.Profesor;
import com.ista.backend.persistence.enums.SexoStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfesorRepository extends JpaRepository<Profesor,Long> {

    public List<Profesor> findAllByGenero(SexoStatus genero);

    public Optional<Profesor> findByCedula(String cedula);
    Optional<Profesor> existsByCedula (String cedula);
}
