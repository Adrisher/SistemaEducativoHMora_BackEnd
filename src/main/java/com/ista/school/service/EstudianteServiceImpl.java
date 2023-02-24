package com.ista.school.service;

import com.ista.school.model.entity.Estudiante;
import com.ista.school.repository.BaseRepository;
import com.ista.school.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
