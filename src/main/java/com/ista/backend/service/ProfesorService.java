package com.ista.backend.service;

import com.ista.backend.persistence.entity.Profesor;
import com.ista.backend.persistence.enums.SexoStatus;
import com.ista.backend.service.dto.ProfesorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface ProfesorService {

    public Iterable<Profesor> listarTodo();
    public Page<Profesor> listarTodo(Pageable pageable);
    public Optional<Profesor> buscarPorId(Long id);
    public List<Profesor> listarPorGenero(SexoStatus status);
    public Profesor guardar(Profesor profesor);
    public void borrarPorId(Long id);
    public void uploadFile(InputStream inputStream);

    public boolean existsById(Long id);


}
