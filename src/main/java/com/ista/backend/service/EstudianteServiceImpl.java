package com.ista.backend.service;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Estudiante;
import com.ista.backend.persistence.enums.SexoStatus;
import com.ista.backend.persistence.repository.EstudianteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService{

    private final EstudianteRepository estudianteRepository;

    public EstudianteServiceImpl(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public Iterable<Estudiante> listarTodo() {
        return this.estudianteRepository.findAll();
    }

    @Override
    public Page<Estudiante> listarTodo(Pageable pageable) {
        return this.estudianteRepository.findAll(pageable);
    }

    @Override
    public Optional<Estudiante> buscarPorId(Long id) {
        return this.estudianteRepository.findById(id);
    }

    @Override
    public Optional<Estudiante> buscarPorCedula(String cedula) {
        return this.estudianteRepository.findByCedula(cedula);
    }

    @Override
    public List<Estudiante> listarPorGenero(SexoStatus status) {
        return this.estudianteRepository.findAllByGenero(status);
    }

    @Override
    public Estudiante guardar(Estudiante estudiante) {
        return this.estudianteRepository.save(estudiante);
    }

    @Override
    public void borrarPorId(Long id) {
        Optional<Estudiante> optional=this.estudianteRepository.findById(id);
        if (optional.isEmpty()){
            throw new SistemaEducativoExceptions("estudiante no encontrado", HttpStatus.NOT_FOUND);
        }

    }
}
