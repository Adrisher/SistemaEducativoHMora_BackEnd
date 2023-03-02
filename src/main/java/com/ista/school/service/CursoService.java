package com.ista.school.service;

import com.ista.school.model.entity.Curso;

import java.util.List;

public interface CursoService extends BaseService<Curso, Long> {

    List<Curso> findByCicloOrParalelo(String filtro);

    boolean exist(String ciclo, String paralelo);

    Curso findByCiclo(String ciclo);

    Curso buscarPorCicloParalelo(String cicloStatus, String paralelo);


}
