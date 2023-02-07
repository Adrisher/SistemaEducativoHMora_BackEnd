package com.ista.backend.service;

import com.ista.backend.persistence.entity.Quimestre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface QuimestreService {

    public Iterable<Quimestre> listarTodo();
    public Page<Quimestre> listarTodo(Pageable pageable);
    public Optional<Quimestre> buscarPorId(Long id);
    public Quimestre guardar(Quimestre quimestre);
    public void borrarPorId(Long id);

}
