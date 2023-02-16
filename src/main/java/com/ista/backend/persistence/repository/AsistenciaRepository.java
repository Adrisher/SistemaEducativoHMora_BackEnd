package com.ista.backend.persistence.repository;

import com.ista.backend.persistence.entity.Asistencia;
import com.ista.backend.persistence.entity.Estudiante;
import com.ista.backend.persistence.enums.AsistenciaStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AsistenciaRepository extends JpaRepository<Asistencia,Long> {

    //mostrar Asistencia por estado ASISTE, NO_ASISTE
    public List<Asistencia> findAllByAsitencia(AsistenciaStatus estado);
    public Optional<Asistencia> findByEstudianteAndFecha(Estudiante estudiante,Date fecha);
    public Optional<Asistencia> findByFechaAndEstudiante(Date date,Estudiante estudiante);
    public List<Asistencia> findAllByEstudiante(Estudiante estudiante);
}
