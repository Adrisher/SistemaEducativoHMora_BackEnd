package com.ista.backend.service;

import com.ista.backend.persistence.entity.Matricula_detalle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface Matricula_detalleService {

    public Iterable<Matricula_detalle> listarTodo();
    public Page<Matricula_detalle> listarTodo(Pageable pageable);
    public Optional<Matricula_detalle> buscarPorId(Long id);
    public Matricula_detalle guardar(Matricula_detalle detalle);
    public void borrarPorId(Long id);
}
