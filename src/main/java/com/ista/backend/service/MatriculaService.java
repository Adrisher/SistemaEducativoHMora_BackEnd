package com.ista.backend.service;

import com.ista.backend.persistence.entity.Matricula;
import com.ista.backend.persistence.enums.CicloStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MatriculaService {

    public Iterable<Matricula> listarTodo();
    public Page<Matricula> listarTodo(Pageable pageable);
    public Optional<Matricula> buscarPorId(Long id);
    public Matricula guardar(Matricula matricula);
    public void borrarPorId(Long id);
}
