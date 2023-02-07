package com.ista.backend.service;

import com.ista.backend.persistence.entity.Periodo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PeriodoService {

    public Iterable<Periodo> listarTodo();
    public Page<Periodo> listarTodo(Pageable pageable);
    public Optional<Periodo> buscarPorId(Long id);
    public Periodo guardar(Periodo periodo);
    public void borrarPorId(Long id);
}
