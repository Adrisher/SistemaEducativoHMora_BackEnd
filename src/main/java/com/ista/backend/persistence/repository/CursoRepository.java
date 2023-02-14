package com.ista.backend.persistence.repository;

import com.ista.backend.persistence.entity.Curso;
import com.ista.backend.persistence.enums.CicloStatus;
import com.ista.backend.persistence.enums.ParaleloStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso,Long> {

    public List<Curso> findAllByParalelo(ParaleloStatus paralelo);
    Optional<Curso> findByCiclo(CicloStatus ciclo);
    Optional<Curso> findByParalelo(ParaleloStatus paralelo);
    Optional<Curso> findByCicloAndParalelo(CicloStatus ciclo,ParaleloStatus paralelo);
}
