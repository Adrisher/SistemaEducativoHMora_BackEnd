package com.ista.backend.service;

import com.ista.backend.persistence.entity.Calificaciones;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CalificacionesService {

    public Iterable<Calificaciones> listarTodo();
    public Page<Calificaciones> listarTodo(Pageable pageable);

    public Optional<Calificaciones> buscarPorId(Long id);
    public Calificaciones guardar(Calificaciones calificaciones);
    public void borrarPorId(Long id);
}
