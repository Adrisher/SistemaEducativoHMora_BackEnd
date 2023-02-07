package com.ista.backend.service;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Matricula_detalle;
import com.ista.backend.persistence.repository.Matricula_detalleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatriculaDetalleServiceImpl implements Matricula_detalleService{

    private final Matricula_detalleRepository matriculaDetalleRepository;

    public MatriculaDetalleServiceImpl(Matricula_detalleRepository matriculaDetalleRepository) {
        this.matriculaDetalleRepository = matriculaDetalleRepository;
    }

    @Override
    public Iterable<Matricula_detalle> listarTodo() {
        return this.matriculaDetalleRepository.findAll();
    }

    @Override
    public Page<Matricula_detalle> listarTodo(Pageable pageable) {
        return this.matriculaDetalleRepository.findAll(pageable);
    }

    @Override
    public Optional<Matricula_detalle> buscarPorId(Long id) {
        return this.matriculaDetalleRepository.findById(id);
    }

    @Override
    public Matricula_detalle guardar(Matricula_detalle detalle) {
        return this.matriculaDetalleRepository.save(detalle);
    }

    @Override
    public void borrarPorId(Long id) {
        Optional<Matricula_detalle> optional=this.matriculaDetalleRepository.findById(id);
        if (optional.isEmpty()){
            throw new SistemaEducativoExceptions("detalle no encontrado", HttpStatus.NOT_FOUND);
        }
        this.matriculaDetalleRepository.deleteById(id);
    }
}
