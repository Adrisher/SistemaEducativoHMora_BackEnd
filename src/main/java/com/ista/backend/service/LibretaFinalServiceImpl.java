package com.ista.backend.service;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Libreta_final;
import com.ista.backend.persistence.repository.LibretaFinalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibretaFinalServiceImpl implements LibretaFinalService{

    private final LibretaFinalRepository libretaFinalRepository;

    public LibretaFinalServiceImpl(LibretaFinalRepository libretaFinalRepository) {
        this.libretaFinalRepository = libretaFinalRepository;
    }

    @Override
    public Iterable<Libreta_final> listarTodo() {
        return this.libretaFinalRepository.findAll();
    }

    @Override
    public Page<Libreta_final> listarTodo(Pageable pageable) {
        return this.libretaFinalRepository.findAll(pageable);
    }

    @Override
    public Optional<Libreta_final> buscarPorId(Long id) {
        return this.libretaFinalRepository.findById(id);
    }

    @Override
    public Libreta_final guardar(Libreta_final libretaFinal) {
        return this.libretaFinalRepository.save(libretaFinal);
    }

    @Override
    public void borrarPorId(Long id) {

        Optional<Libreta_final> optional=this.libretaFinalRepository.findById(id);
        if (optional.isEmpty()){
            throw new SistemaEducativoExceptions("libreta no encontrada", HttpStatus.NOT_FOUND);
        }
        this.libretaFinalRepository.deleteById(id);
    }
}
