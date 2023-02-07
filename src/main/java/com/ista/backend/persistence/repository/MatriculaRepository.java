package com.ista.backend.persistence.repository;

import com.ista.backend.persistence.entity.Matricula;
import com.ista.backend.persistence.enums.CicloStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula,Long> {

    public List<Matricula> findAllByCiclo(CicloStatus ciclo);
}
