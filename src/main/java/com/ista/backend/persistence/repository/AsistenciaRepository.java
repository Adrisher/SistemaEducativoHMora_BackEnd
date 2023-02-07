package com.ista.backend.persistence.repository;

import com.ista.backend.persistence.entity.Asistencia;
import com.ista.backend.persistence.enums.AsistenciaStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia,Long> {

    //mostrar Asistencia por estado ASISTE, NO_ASISTE
    public List<Asistencia> findAllByAsistencia(AsistenciaStatus estado);
}
