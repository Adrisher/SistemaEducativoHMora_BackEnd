package com.ista.school.repository;

import com.ista.school.model.entity.Curso;
import com.ista.school.model.entity.Materia;
import com.ista.school.model.entity.Profesor;
import com.ista.school.model.entity.ProfesorCursoMateria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorCursoMateriaRepository extends BaseRepository<ProfesorCursoMateria, Long> {

     ProfesorCursoMateria findByProfesorAndCursoAndMateria(Profesor profesor, Curso curso, Materia materia);

     List<ProfesorCursoMateria> findByCursoIdCurso(Long curso);

}
