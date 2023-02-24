package com.ista.school.repository;

import com.ista.school.model.entity.Curso;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends BaseRepository<Curso, Long> {
}
