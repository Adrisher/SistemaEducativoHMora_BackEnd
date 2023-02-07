package com.ista.backend.service;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Parcial;
import com.ista.backend.persistence.repository.ParcialRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParcialServiceImpl implements ParcialService{

    private final ParcialRepository parcialRepository;

    public ParcialServiceImpl(ParcialRepository parcialRepository) {
        this.parcialRepository = parcialRepository;
    }

    @Override
    public Iterable<Parcial> listarTodo() {
        return this.parcialRepository.findAll();
    }

    @Override
    public Page<Parcial> listarTodo(Pageable pageable) {
        return this.parcialRepository.findAll(pageable);
    }

    @Override
    public Optional<Parcial> buscarPorId(Long id) {
        return this.parcialRepository.findById(id);
    }

    @Override
    public Parcial guardar(Parcial parcial) {
        return this.parcialRepository.save(parcial);
    }

    @Override
    public void borrarPorId(Long id) {
        Optional<Parcial> optional=this.parcialRepository.findById(id);
        if (optional.isEmpty()){
            throw new SistemaEducativoExceptions("parcial no encontrado", HttpStatus.NOT_FOUND);
        }

    }
}
