package com.ista.school.service;

import com.ista.school.model.entity.Estudiante;
import com.ista.school.repository.BaseRepository;
import com.ista.school.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl extends BaseServiceImpl<Estudiante, Long> implements EstudianteService {

    @Autowired
    private EstudianteRepository reposistory;

    public EstudianteServiceImpl(BaseRepository<Estudiante, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public List<Estudiante> findAll() {
        return reposistory.findAll();
    }

    @Override
    public Optional<Estudiante> findById(Long id) {
        return reposistory.findById(id);
    }

    @Override
    public Estudiante save(Estudiante entity) {
        if (reposistory.findByCedula(entity.getCedula()) == null) {
            return reposistory.save(entity);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        reposistory.deleteById(id);
    }

    @Override
    public Estudiante update(Estudiante t, Long id) {
        Optional<Estudiante> current = reposistory.findById(id);
        Estudiante update = current.get();
        update = reposistory.save(t);
        return update;
    }

}
