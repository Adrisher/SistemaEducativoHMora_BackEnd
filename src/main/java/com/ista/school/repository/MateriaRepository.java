package com.ista.school.repository;

import com.ista.school.model.entity.Materia;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriaRepository extends BaseRepository<Materia, Long> {

    Materia findByNombre(String nombre);
    List<Materia> findByNombreContainingIgnoreCase(String nombre);

}
