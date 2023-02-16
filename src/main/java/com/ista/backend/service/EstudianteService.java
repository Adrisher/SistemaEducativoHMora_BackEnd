package com.ista.backend.service;

import com.ista.backend.persistence.entity.Estudiante;
import com.ista.backend.persistence.entity.Representante;
import com.ista.backend.persistence.enums.SexoStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EstudianteService {

    public Iterable<Estudiante> listarTodo();
    public Page<Estudiante> listarTodo(Pageable pageable);
    public Optional<Estudiante> buscarPorId(Long id);
    public Optional<Estudiante> buscarPorCedula(String cedula);
    public Optional<Estudiante> buscarPorCedulaRepresentante(String cedula,Representante representante);
    public List<Estudiante> listarPorGenero(SexoStatus status);
    public Estudiante guardar(Estudiante estudiante);
    public void borrarPorId(Long id);
    public List<Estudiante> findAllByRepresentante(Long id);


}
