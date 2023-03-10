package com.ista.school.repository;

import com.ista.school.model.entity.Profesor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorRepository extends BaseRepository<Profesor, Long> {

    Profesor findByCedula(String cedula);

    List<Profesor> findByEstadoTrueAndCedulaContainingIgnoreCase(String cedula);

    List<Profesor> findByEstadoTrue();

    Profesor findByEstadoTrueAndCedula(String username);

    List<Profesor> findByCedulaContainingIgnoreCaseOrPrimerApellidoContainingIgnoreCaseAndEstadoTrue(String cedula, String apellido);


}
