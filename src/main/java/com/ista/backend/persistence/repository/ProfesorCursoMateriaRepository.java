package com.ista.backend.persistence.repository;

import com.ista.backend.persistence.entity.Curso;
import com.ista.backend.persistence.entity.Materia;
import com.ista.backend.persistence.entity.Profesor;
import com.ista.backend.persistence.entity.ProfesorCursoMateria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfesorCursoMateriaRepository extends JpaRepository<ProfesorCursoMateria,Long> {

    public Optional<ProfesorCursoMateria> findByProfesor(Profesor profesor);

    public Optional<ProfesorCursoMateria> findByProfesorAndCursoAndMateria(Profesor profesor,
                                                                           Curso curso,
                                                                           Materia materia);



}
