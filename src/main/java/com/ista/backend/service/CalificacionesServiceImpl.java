package com.ista.backend.service;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Calificaciones;
import com.ista.backend.persistence.repository.CalificacionesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CalificacionesServiceImpl implements CalificacionesService {

    private final CalificacionesRepository calificacionesRepository;

    public CalificacionesServiceImpl(CalificacionesRepository calificacionesRepository) {
        this.calificacionesRepository = calificacionesRepository;
    }


    @Override
    @Transactional(readOnly=true)
    public Iterable<Calificaciones> listarTodo() {
        return this.calificacionesRepository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public Page<Calificaciones> listarTodo(Pageable pageable) {
        return this.calificacionesRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<Calificaciones> buscarPorId(Long id) {
        Optional<Calificaciones> optional=this.calificacionesRepository.findById(id);
        if (optional.isEmpty()){
            throw new SistemaEducativoExceptions("asistencia no encontrada", HttpStatus.NOT_FOUND);
        }
        return this.calificacionesRepository.findById(id);
    }

    @Override
    @Transactional(readOnly=true)
    public Calificaciones guardar(Calificaciones calificaciones) {
        return this.calificacionesRepository.save(calificaciones);
    }

    @Override
    @Transactional(readOnly=true)
    public void borrarPorId(Long id) {
        Optional<Calificaciones> optional=this.calificacionesRepository.findById(id);
        if (optional.isEmpty()){
            throw new SistemaEducativoExceptions("parametro no encontrada", HttpStatus.NOT_FOUND);
        }
        this.calificacionesRepository.deleteById(id);
    }
}
