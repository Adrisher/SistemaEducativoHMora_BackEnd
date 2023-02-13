package com.ista.backend.service;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Representante;
import com.ista.backend.persistence.enums.SexoStatus;
import com.ista.backend.persistence.repository.RepresentanteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepresentanteServiceImpl implements RepresentanteService{

    private final RepresentanteRepository representanteRepository;

    public RepresentanteServiceImpl(RepresentanteRepository representanteRepository) {
        this.representanteRepository = representanteRepository;
    }

    @Override
    public Iterable<Representante> listarTodo() {
        return this.representanteRepository.findAll();
    }

    @Override
    public Page<Representante> listarTodo(Pageable pageable) {
        return this.representanteRepository.findAll(pageable);
    }

    @Override
    public Optional<Representante> buscarPorId(Long id) {
        return this.representanteRepository.findById(id);
    }

    @Override
    public Optional<Representante> buscarPorCedula(String cedula) {
        return this.representanteRepository.findByCedula(cedula);
    }

    @Override
    public List<Representante> listarPorGenero(SexoStatus status) {
        return this.representanteRepository.findAllByGenero(status);
    }

    @Override
    public Representante guardar(Representante representante) {
        return this.representanteRepository.save(representante);
    }

    @Override
    public void borrarPorId(Long id) {
        Optional<Representante> optional=this.representanteRepository.findById(id);
        if (optional.isEmpty()){
            throw new SistemaEducativoExceptions("Representante no encontrado", HttpStatus.NOT_FOUND);
        }
        this.representanteRepository.deleteById(id);
    }

    @Override
    public boolean existsByCedula(String cedula){
        return this.representanteRepository.existsByCedula(cedula);
    }
}
