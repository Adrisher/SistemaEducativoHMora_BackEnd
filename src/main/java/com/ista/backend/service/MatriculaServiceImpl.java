package com.ista.backend.service;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Matricula;
import com.ista.backend.persistence.enums.CicloStatus;
import com.ista.backend.persistence.repository.MatriculaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaServiceImpl implements MatriculaService{

    private final MatriculaRepository matriculaRepository;

    public MatriculaServiceImpl(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    @Override
    public Iterable<Matricula> listarTodo() {
        return this.matriculaRepository.findAll();
    }

    @Override
    public Page<Matricula> listarTodo(Pageable pageable) {
        return this.matriculaRepository.findAll(pageable);
    }

    @Override
    public Optional<Matricula> buscarPorId(Long id) {
        return this.matriculaRepository.findById(id);
    }


    @Override
    public Matricula guardar(Matricula matricula) {
        return this.matriculaRepository.save(matricula);
    }

    @Override
    public void borrarPorId(Long id) {
        Optional<Matricula> optional=this.matriculaRepository.findById(id);
        if (optional.isEmpty()){
            throw new SistemaEducativoExceptions("matricula no encontrado", HttpStatus.NOT_FOUND);
        }
        this.matriculaRepository.deleteById(id);

    }
}
