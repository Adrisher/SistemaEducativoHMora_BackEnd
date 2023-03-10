package com.ista.school.service;

import com.ista.school.model.entity.Curso;
import com.ista.school.model.entity.Materia;
import com.ista.school.model.entity.Profesor;
import com.ista.school.model.entity.ProfesorCursoMateria;

import java.util.List;

public interface ProfesorCursoMateriaService extends BaseService<ProfesorCursoMateria, Long> {

    ProfesorCursoMateria buscarProfesorCursoMateria(Profesor profesor, Curso curso, Materia materia);

    List<ProfesorCursoMateria> findByCurso(Long idCurso);

}
