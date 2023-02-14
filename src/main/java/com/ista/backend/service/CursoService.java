package com.ista.backend.service;

import com.ista.backend.persistence.entity.Curso;
import com.ista.backend.persistence.enums.CicloStatus;
import com.ista.backend.persistence.enums.ParaleloStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    public Iterable<Curso> listarTodo();
    public Page<Curso> listarTodo(Pageable pageable);
    public Optional<Curso> buscarPorId(Long id);
    public List<Curso> listarPorParalelo(ParaleloStatus status);
    public Curso guardar(Curso curso);
    public void borrarPorId(Long id);

    public Optional<Curso> buscarPorCiclo(CicloStatus status);
    public Optional<Curso> buscarPorParalelo(ParaleloStatus status);
    public Optional<Curso> buscarPorCicloParalelo(CicloStatus cicloStatus, ParaleloStatus paralelo);
}
