package com.ista.school.repository;

import com.ista.school.model.entity.Curso;
import com.ista.school.model.entity.Materia;
import com.ista.school.model.entity.Profesor;
import com.ista.school.model.entity.ProfesorCursoMateria;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorCursoMateriaRepository extends BaseRepository<ProfesorCursoMateria, Long> {
     ProfesorCursoMateria findByProfesorAndCursoAndMateria(Profesor profesor, Curso curso, Materia materia);
}
