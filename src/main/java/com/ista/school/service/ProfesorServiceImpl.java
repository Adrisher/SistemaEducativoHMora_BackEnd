package com.ista.school.service;

import com.ista.school.model.entity.Profesor;
import com.ista.school.repository.BaseRepository;
import com.ista.school.repository.ProfesorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorServiceImpl extends BaseServiceImpl<Profesor, Long> implements ProfesorService {

    @Autowired
    private ProfesorRepository repository;

    public ProfesorServiceImpl(BaseRepository<Profesor, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public Profesor findByCedula(String cedula) {
        return repository.findByCedula(cedula);
    }

    @Override
    public List<Profesor> findByCedulaTrue(String cedula) {
        return repository.findByEstadoTrueAndCedulaContainingIgnoreCase(cedula);
    }

    @Override
    public List<Profesor> findByTrue() {
        return repository.findByEstadoTrue();
    }

    @Override
    public boolean isActive(String username) {
        Profesor profesor = repository.findByEstadoTrueAndCedula(username);
        if (profesor != null) {
            return true;
        }
        return false;
    }


}
