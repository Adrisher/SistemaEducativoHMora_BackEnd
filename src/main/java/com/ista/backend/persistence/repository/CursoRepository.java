package com.ista.backend.persistence.repository;

import com.ista.backend.persistence.entity.Curso;
import com.ista.backend.persistence.enums.ParaleloStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso,Long> {

    public List<Curso> findAllByParalelo(ParaleloStatus paralelo);
}
