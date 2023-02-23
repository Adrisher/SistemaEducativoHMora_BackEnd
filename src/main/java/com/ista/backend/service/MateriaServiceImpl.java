package com.ista.backend.service;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Materia;
import com.ista.backend.persistence.enums.MateriaStatus;
import com.ista.backend.persistence.repository.MateriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MateriaServiceImpl implements MateriaService{

    private final MateriaRepository materiaRepository;

    public MateriaServiceImpl(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    @Override
    public Iterable<Materia> listarTodo() {
        return this.materiaRepository.findAll();
    }

    @Override
    public Page<Materia> listarTodo(Pageable pageable) {
        return this.materiaRepository.findAll(pageable);
    }

    @Override
    public Optional<Materia> buscarPorId(Long id) {
        return this.materiaRepository.findById(id);
    }

    @Override
    public Materia guardar(Materia materia) {
        return this.materiaRepository.save(materia);
    }

    @Override
    public void borrarPorId(Long id) {
        Optional<Materia> optional=this.materiaRepository.findById(id);
        if (optional.isEmpty()){
            throw new SistemaEducativoExceptions("materia no encontrada", HttpStatus.NOT_FOUND);
        }
        this.materiaRepository.deleteById(id);

    }

    @Override
    public Optional<Materia> buscarPorMateria(String status) {
        return this.materiaRepository.findByMateriaDetalle(status);
    }



}
