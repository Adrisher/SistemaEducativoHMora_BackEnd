package com.ista.school.service;

import com.ista.school.model.entity.Estudiante;

import java.util.List;

public interface EstudianteService extends BaseService<Estudiante, Long> {

    Estudiante findByCedula(String cedula);

    List<Estudiante> findEstudent(String cedula, String nombre, String apellido);

    List<Estudiante> findByEstadoTrue();

    List<Estudiante> findByEstadoTrueAndCedula(String cedula);

}
