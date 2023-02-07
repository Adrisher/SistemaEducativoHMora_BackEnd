package com.ista.backend.service;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Asistencia;
import com.ista.backend.persistence.enums.AsistenciaStatus;
import com.ista.backend.persistence.repository.AsistenciaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaServiceImpl implements AsistenciaService{

    private final AsistenciaRepository asistenciaRepository;

    public AsistenciaServiceImpl(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    @Override
    @Transactional(readOnly=true)
    public Iterable<Asistencia> listarTodo() {
        return this.asistenciaRepository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public Page<Asistencia> listarTodo(Pageable pageable) {
        return this.asistenciaRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<Asistencia> buscarPorId(Long id) {
        Optional<Asistencia> optional=this.asistenciaRepository.findById(id);
        if (optional.isEmpty()){
            throw new SistemaEducativoExceptions("asistencia no encontrada", HttpStatus.NOT_FOUND);
        }
        return this.asistenciaRepository.findById(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Asistencia> listarPorAsistencia(AsistenciaStatus status) {
        return this.asistenciaRepository.findAllByAsistencia(status);
    }

    @Override
    @Transactional(readOnly=true)
    public Asistencia guardar(Asistencia asistencia) {
        return this.asistenciaRepository.save(asistencia);
    }

    @Override
    @Transactional(readOnly=true)
    public void borrarPorId(Long id) {
        Optional<Asistencia> optional=this.asistenciaRepository.findById(id);
        if (optional.isEmpty()){
            throw new SistemaEducativoExceptions("asistencia no encontrada", HttpStatus.NOT_FOUND);
        }
        this.asistenciaRepository.deleteById(id);
    }
}
