package com.ista.school.repository;

import com.ista.school.model.entity.Curso;
import com.ista.school.model.entity.Estudiante;
import com.ista.school.model.entity.Matricula;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends BaseRepository<Matricula, Long> {

    Matricula findByEstudiante(Estudiante e);
    Matricula findByEstudianteAndCurso(Estudiante e, Curso c);
}
