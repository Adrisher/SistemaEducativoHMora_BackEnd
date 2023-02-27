package com.ista.school.repository;

import com.ista.school.model.entity.Estudiante;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends BaseRepository<Estudiante, Long> {

    Estudiante findByCedula(String cedula);

    @Query(
            value = "SELECT * FROM estudiante e " +
                    "WHERE (e.nombre = :nombre OR e.cedula = :cedula OR e.primer_apellido = :apellido) AND estado = TRUE " +
                    "ORDER BY nombre ASC, primer_apellido",
            nativeQuery = true
    )
    List<Estudiante> findEstudent(@Param("cedula")String cedula,
                             @Param("nombre")String nombre,
                             @Param("apellido")String apellido);


    List<Estudiante> findByEstadoTrue();

    List<Estudiante> findByEstadoTrueAndCedula(String cedula);

}
