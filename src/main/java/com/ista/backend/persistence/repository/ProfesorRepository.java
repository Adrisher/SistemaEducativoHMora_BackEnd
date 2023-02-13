package com.ista.backend.persistence.repository;

import com.ista.backend.persistence.entity.Profesor;
import com.ista.backend.persistence.enums.SexoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProfesorRepository extends JpaRepository<Profesor,Long> {

    public List<Profesor> findAllByGenero(SexoStatus genero);

    public Optional<Profesor> findByCedula(String cedula);
    Optional<Profesor> existsByCedula (String cedula);

    @Transactional
    @Modifying
    @Query(value = "UPDATE public.profesor SET estado=false WHERE id_profesor=:id",nativeQuery = true)
    public void darDeBaja(@Param("id")Long id);
}
