package com.ista.backend.service;

import com.ista.backend.persistence.entity.Parcial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ParcialService {

    public Iterable<Parcial> listarTodo();
    public Page<Parcial> listarTodo(Pageable pageable);
    public Optional<Parcial> buscarPorId(Long id);
    public Parcial guardar(Parcial parcial);
    public void borrarPorId(Long id);
}
