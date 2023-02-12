package com.ista.backend.service;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.ProfesorCursoMateria;
import com.ista.backend.persistence.repository.ProfesorCursoMateriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfesorMateriaCursoServiceImpl implements ProfesorCursoMateriaService{

    private final ProfesorCursoMateriaRepository repository;

    public ProfesorMateriaCursoServiceImpl(ProfesorCursoMateriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<ProfesorCursoMateria> listarTodo() {
        return this.repository.findAll();
    }

    @Override
    public Page<ProfesorCursoMateria> listarTodo(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Override
    public Optional<ProfesorCursoMateria> buscarPorId(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public ProfesorCursoMateria guardar(ProfesorCursoMateria profesorCursoMateria) {
        return this.repository.save(profesorCursoMateria);
    }

    @Override
    public void borrarPorId(Long id) {
        Optional<ProfesorCursoMateria> optional=this.repository.findById(id);
        if (optional.isEmpty()){
            throw new SistemaEducativoExceptions("NO ENCONTRADO", HttpStatus.NOT_FOUND);
        }
        this.repository.deleteById(id);
    }
}
