package com.ista.backend.service;

import com.ista.backend.persistence.entity.Materia_Curso_Profesor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MateriaCursoProfesorService {

    public Iterable<Materia_Curso_Profesor> listarTodo();
    public Page<Materia_Curso_Profesor> listarTodo(Pageable pageable);
    public Optional<Materia_Curso_Profesor> buscarPorId(Long id);
    public Materia_Curso_Profesor guardar(Materia_Curso_Profesor materiaCursoProfesor);
    public void borrarPorId(Long id);

}
