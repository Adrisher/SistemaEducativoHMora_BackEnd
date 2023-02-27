package com.ista.school.repository;

import com.ista.school.model.entity.Curso;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends BaseRepository<Curso, Long> {

    @Query(
            value = "SELECT * FROM curso c WHERE c.ciclo = :filtro OR c.paralelo = :filtro " +
                    "ORDER BY paralelo ASC",
            nativeQuery = true
    )
    List<Curso> findByCicloOrParalelo(@Param("filtro")String filtro);

    @Query(
        value = "SELECT * FROM curso c WHERE c.ciclo = :ciclo AND c.paralelo = :paralelo",
        nativeQuery = true
    )
    boolean exist(@Param("ciclo") String ciclo, @Param("paralelo") String paralelo);

}
