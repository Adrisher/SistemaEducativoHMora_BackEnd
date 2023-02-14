package com.ista.backend.persistence.repository;

import com.ista.backend.persistence.entity.Curso;
import com.ista.backend.persistence.entity.Materia;
import com.ista.backend.persistence.entity.Profesor;
import com.ista.backend.persistence.entity.ProfesorCursoMateria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfesorCursoMateriaRepository extends JpaRepository<ProfesorCursoMateria,Long> {

    public List<ProfesorCursoMateria> findByProfesor(Profesor profesor);

}
