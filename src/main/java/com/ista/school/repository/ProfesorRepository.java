package com.ista.school.repository;

import com.ista.school.model.entity.Profesor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends BaseRepository<Profesor, Long> {

    Profesor findByCedula(String cedula);

}
