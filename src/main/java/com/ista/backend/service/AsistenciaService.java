package com.ista.backend.service;

import com.ista.backend.persistence.entity.Asistencia;
import com.ista.backend.persistence.entity.Estudiante;
import com.ista.backend.persistence.enums.AsistenciaStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AsistenciaService {

    public Iterable<Asistencia> listarTodo();
    public Page<Asistencia> listarTodo(Pageable pageable);
    public Optional<Asistencia> buscarPorId(Long id);
    public List<Asistencia> listarPorAsistencia(AsistenciaStatus status);
    public Asistencia guardar(Asistencia asistencia);
    public void borrarPorId(Long id);
    public Optional<Asistencia> bucarPorEstudianteFecha(Date fecha, Estudiante estudiante);
    public Optional<Asistencia> buscarPorCedulaFecha(String cedula, Date fecha);
    public List<Asistencia> listarPorAlumno(String cedula);



}
