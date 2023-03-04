package com.ista.school.repository;

import com.ista.school.model.entity.Detalle;
import com.ista.school.model.entity.Materia;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleRepository extends BaseRepository<Detalle, Long> {
    Detalle findByMateria(Materia m);
}
