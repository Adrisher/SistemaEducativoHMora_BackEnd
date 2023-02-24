package com.ista.school.repository;

import com.ista.school.model.entity.Profesor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorRepository extends BaseRepository<Profesor, Long> {

    Profesor findByCedula(String cedula);

    List<Profesor> findByNombreContainingIgnoreCase(String nombre);

}
