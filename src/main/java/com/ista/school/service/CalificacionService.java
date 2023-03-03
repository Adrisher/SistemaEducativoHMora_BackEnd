package com.ista.school.service;

import com.ista.school.model.entity.Calificacion;

public interface CalificacionService extends BaseService<Calificacion, Long> {

    void agregarCalificacion(Long id_estudiante,Long id_parcial,Calificacion c);

}
