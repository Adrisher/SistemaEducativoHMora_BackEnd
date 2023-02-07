package com.ista.backend.service;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Quimestre;
import com.ista.backend.persistence.repository.QuimestreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuimestreServiceImpl implements QuimestreService{

    private final QuimestreRepository quimestreRepository;

    public QuimestreServiceImpl(QuimestreRepository quimestreRepository) {
        this.quimestreRepository = quimestreRepository;
    }

    @Override
    public Iterable<Quimestre> listarTodo() {
        return this.quimestreRepository.findAll();
    }

    @Override
    public Page<Quimestre> listarTodo(Pageable pageable) {
        return this.quimestreRepository.findAll(pageable);
    }

    @Override
    public Optional<Quimestre> buscarPorId(Long id) {
        return this.quimestreRepository.findById(id);
    }

    @Override
    public Quimestre guardar(Quimestre quimestre) {
        return this.quimestreRepository.save(quimestre);
    }

    @Override
    public void borrarPorId(Long id) {
        Optional<Quimestre> optional=this.quimestreRepository.findById(id);
        if (optional.isEmpty()){
                throw new SistemaEducativoExceptions("Quimestre no encontrado", HttpStatus.NOT_FOUND);
        }
        this.quimestreRepository.deleteById(id);
    }
}
