package com.ista.backend.service;

import com.ista.backend.persistence.entity.Libreta_final;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LibretaFinalService {

    public Iterable<Libreta_final> listarTodo();
    public Page<Libreta_final> listarTodo(Pageable pageable);
    public Optional<Libreta_final> buscarPorId(Long id);
    public Libreta_final guardar(Libreta_final libretaFinal);
    public void borrarPorId(Long id);
}
