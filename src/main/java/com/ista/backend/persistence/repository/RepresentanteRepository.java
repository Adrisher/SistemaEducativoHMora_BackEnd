package com.ista.backend.persistence.repository;

import com.ista.backend.persistence.entity.Representante;
import com.ista.backend.persistence.enums.SexoStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepresentanteRepository extends JpaRepository<Representante,Long> {

   public List<Representante> findAllByGenero(SexoStatus genero);
   public Optional<Representante> findByCedula(String cedula);
   public boolean existsById(Long id);
}
