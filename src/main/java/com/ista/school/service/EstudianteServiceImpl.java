package com.ista.school.service;

import com.ista.school.model.entity.Estudiante;
import com.ista.school.repository.BaseRepository;
import com.ista.school.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteServiceImpl extends BaseServiceImpl<Estudiante, Long> implements EstudianteService {

    @Autowired
    private EstudianteRepository reposistory;

    public EstudianteServiceImpl(BaseRepository<Estudiante, Long> baseRepository) {
        super(baseRepository);
    }


    @Override
    public Estudiante findByCedula(String cedula) {
        return reposistory.findByCedula(cedula);
    }

    @Override
    public List<Estudiante> findEstudent(String cedula, String nombre, String apellido) {
        return reposistory.findEstudent(cedula, nombre, apellido);
    }

    @Override
    public List<Estudiante> findByEstadoTrue() {
        return reposistory.findByEstadoTrue();
    }

    @Override
    public List<Estudiante> findByEstadoTrueAndCedula(String cedula) {
        return reposistory.findByEstadoTrueAndCedulaContainingIgnoreCase(cedula);
    }

}
