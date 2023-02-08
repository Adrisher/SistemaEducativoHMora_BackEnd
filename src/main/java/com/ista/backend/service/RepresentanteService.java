package com.ista.backend.service;

import com.ista.backend.persistence.entity.Representante;
import com.ista.backend.persistence.enums.SexoStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RepresentanteService {

    public Iterable<Representante> listarTodo();
    public Page<Representante> listarTodo(Pageable pageable);
    public Optional<Representante> buscarPorId(Long id);
    public Optional<Representante> buscarPorCedula(String cedula);
    public List<Representante> listarPorGenero(SexoStatus status);
    public Representante guardar(Representante representante);
    public void borrarPorId(Long id);

    public boolean existsById(Long id);
}
