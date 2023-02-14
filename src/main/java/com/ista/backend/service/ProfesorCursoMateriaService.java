package com.ista.backend.service;

import com.ista.backend.persistence.entity.ProfesorCursoMateria;
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

    public List<ProfesorCursoMateria> buscarPorProfesor(String cedula);

    public List<?> listarCursoMateriaPorProfesor(String cedula);
}
