package com.ista.backend.service;

import com.ista.backend.persistence.entity.Materia;
import com.ista.backend.persistence.repository.MateriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        return Optional.empty();
    }

    @Override
    public Materia guardar(Materia materia) {
        return null;
    }

    @Override
    public void borrarPorId(Long id) {

    }
}
