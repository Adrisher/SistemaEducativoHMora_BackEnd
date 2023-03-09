package com.ista.school.service;

import com.ista.school.model.entity.Profesor;

import java.util.List;

public interface ProfesorService extends BaseService<Profesor, Long> {

    Profesor findByCedula(String cedula);
    List<Profesor> findByCedulaTrue(String cedula);
    List<Profesor> findByTrue ();
    boolean isActive(String username);

    List<Profesor> seachByCedulaOrApellido(String filtro);

}
