package com.ista.backend.service;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Curso;
import com.ista.backend.persistence.entity.Materia;
import com.ista.backend.persistence.entity.Profesor;
import com.ista.backend.persistence.entity.ProfesorCursoMateria;
import com.ista.backend.persistence.enums.CicloStatus;
import com.ista.backend.persistence.repository.CursoRepository;
import com.ista.backend.persistence.repository.ProfesorCursoMateriaRepository;
import com.ista.backend.persistence.repository.ProfesorRepository;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesorMateriaCursoServiceImpl implements ProfesorCursoMateriaService{

    private final ProfesorCursoMateriaRepository repository;
    private final ProfesorRepository profesorRepository;
    private final CursoRepository cursoRepository;

    public ProfesorMateriaCursoServiceImpl(ProfesorCursoMateriaRepository repository, ProfesorRepository profesorRepository, CursoRepository cursoRepository) {
        this.repository = repository;
        this.profesorRepository = profesorRepository;
        this.cursoRepository = cursoRepository;
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
    public Optional<ProfesorCursoMateria> buscarPorProfesor(Profesor profesor) {
        return this.repository.findByProfesor(profesor);
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

    @Override
    public Optional<ProfesorCursoMateria> buscarProfesorCursoMateria(Profesor profesor, Curso curso, Materia materia) {
        return this.repository.findByProfesorAndCursoAndMateria(profesor,curso,materia);
    }



}
