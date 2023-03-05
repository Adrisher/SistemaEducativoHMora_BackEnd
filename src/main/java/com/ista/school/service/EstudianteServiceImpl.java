package com.ista.school.service;

import com.ista.school.model.entity.Estudiante;
import com.ista.school.model.entity.Profesor;
import com.ista.school.repository.BaseRepository;
import com.ista.school.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteServiceImpl extends BaseServiceImpl<Estudiante, Long> implements EstudianteService {

    @Autowired
    private EstudianteRepository repository;

    public EstudianteServiceImpl(BaseRepository<Estudiante, Long> baseRepository) {
        super(baseRepository);
    }


    @Override
    public Estudiante findByCedula(String cedula) {
        return repository.findByCedula(cedula);
    }

    @Override
    public List<Estudiante> findEstudent(String cedula, String nombre, String apellido) {
        return repository.findEstudent(cedula, nombre, apellido);
    }

    @Override
    public List<Estudiante> findByEstadoTrue() {
        return repository.findByEstadoTrue();
    }

    @Override
    public List<Estudiante> findByEstadoTrueAndCedula(String cedula) {
        return repository.findByEstadoTrueAndCedulaContainingIgnoreCase(cedula);
    }

    @Override
    public boolean isActive(String username) {
        Estudiante estudiante = repository.findByEstadoTrueAndCedula(username);
        if (estudiante != null) {
            return true;
        }
        return false;
    }

}
