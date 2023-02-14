package com.ista.backend.service;

import com.ista.backend.persistence.entity.Curso;
import com.ista.backend.persistence.entity.Materia;
import com.ista.backend.persistence.entity.Profesor;
import com.ista.backend.persistence.entity.ProfesorCursoMateria;
import com.ista.backend.persistence.enums.CicloStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProfesorCursoMateriaService {

    public Iterable<ProfesorCursoMateria> listarTodo();
    public Page<ProfesorCursoMateria> listarTodo(Pageable pageable);
    public Optional<ProfesorCursoMateria> buscarPorId(Long id);
    public ProfesorCursoMateria guardar(ProfesorCursoMateria profesorCursoMateria);
    public void borrarPorId(Long id);

    public List<ProfesorCursoMateria> listarPorProfesor(String cedula);

    public List<?> listarCursoMateriaPorProfesor(String cedula);
    public Optional<ProfesorCursoMateria> buscarPorCiclo(CicloStatus status);

    public Optional<ProfesorCursoMateria> buscarProfesorCursoMateria(Profesor profesor,
                                                                     Curso curso,
                                                                     Materia materia);
}
