package com.ista.school.service;

import com.ista.school.model.entity.Matricula;

public interface MatriculaService extends BaseService<Matricula, Long> {

    Matricula buscarPorEstudiante(Long id);
    Matricula buscarPorEstudianteCurso(Long idEst,Long id_curso);
}
