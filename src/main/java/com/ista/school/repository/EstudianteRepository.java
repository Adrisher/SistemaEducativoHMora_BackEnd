package com.ista.school.repository;

import com.ista.school.model.entity.Estudiante;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends BaseRepository<Estudiante, Long> {

    Estudiante findByCedula(String cedula);

}
