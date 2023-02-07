package com.ista.backend.service;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Periodo;
import com.ista.backend.persistence.repository.PeriodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PeriodoServiceImpl implements PeriodoService{

    private final PeriodoRepository periodoRepository;


    public PeriodoServiceImpl(PeriodoRepository periodoRepository) {
        this.periodoRepository = periodoRepository;
    }

    @Override
    public Iterable<Periodo> listarTodo() {
        return this.periodoRepository.findAll();
    }

    @Override
    public Page<Periodo> listarTodo(Pageable pageable) {
        return this.periodoRepository.findAll(pageable);
    }

    @Override
    public Optional<Periodo> buscarPorId(Long id) {
        return this.periodoRepository.findById(id);
    }

    @Override
    public Periodo guardar(Periodo periodo) {
        return this.periodoRepository.save(periodo);
    }

    @Override
    public void borrarPorId(Long id) {
        Optional<Periodo> optional=this.periodoRepository.findById(id);
        if (optional.isEmpty()){
            throw new SistemaEducativoExceptions("periodo no encontrado", HttpStatus.NOT_FOUND);
        }
        this.periodoRepository.deleteById(id);
    }
}
