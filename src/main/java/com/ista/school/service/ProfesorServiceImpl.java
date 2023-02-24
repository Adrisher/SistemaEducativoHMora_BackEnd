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
@Transactional
public class ProfesorServiceImpl extends BaseServiceImpl<Profesor, Long> implements ProfesorService {

    @Autowired
    private ProfesorRepository repository;

    public ProfesorServiceImpl(BaseRepository<Profesor, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public List<Profesor> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Profesor> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Profesor save(Profesor entity) {
        if (repository.findByCedula(entity.getCedula()) == null) {
            return repository.save(entity);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Profesor update(Profesor t, Long id) {
        return null;
    }

}
