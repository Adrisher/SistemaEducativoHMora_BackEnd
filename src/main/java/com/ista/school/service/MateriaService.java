package com.ista.school.service;

import com.ista.school.model.entity.Materia;

import java.util.List;

public interface MateriaService extends BaseService<Materia, Long> {

    Materia fingdByNombre(String nombre);
    List<Materia> findByNombreContainingIgnoreCase(String nombre);

}
