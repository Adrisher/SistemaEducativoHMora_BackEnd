package com.ista.school.service;

import com.ista.school.model.entity.Representante;
import com.ista.school.repository.BaseRepository;
import com.ista.school.repository.RepresentanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepresentanteServiceImpl extends BaseServiceImpl<Representante, Long> implements RepresentanteService {

    @Autowired
    private RepresentanteRepository repository;

    public RepresentanteServiceImpl(BaseRepository<Representante, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public List<Representante> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Representante> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Representante save(Representante entity) {
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
    public Representante update(Representante t, Long id) {
        Optional<Representante> current = repository.findById(id);
        Representante update = current.get();
        update = repository.save(t);
        return update;
    }

}
