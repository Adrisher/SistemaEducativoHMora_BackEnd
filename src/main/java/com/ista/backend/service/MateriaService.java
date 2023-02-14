package com.ista.backend.service;

import com.ista.backend.persistence.entity.Materia;
import com.ista.backend.persistence.enums.MateriaStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MateriaService {

    public Iterable<Materia> listarTodo();
    public Page<Materia> listarTodo(Pageable pageable);
    public Optional<Materia> buscarPorId(Long id);
    public Materia guardar(Materia materia);
    public void borrarPorId(Long id);
    Optional<Materia> buscarPorMateria(MateriaStatus status);
}
