package com.ista.school.service;

import com.ista.school.model.entity.Materia;
import com.ista.school.repository.BaseRepository;
import com.ista.school.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaServiceImpl extends BaseServiceImpl<Materia, Long> implements MateriaService {

    @Autowired
    private MateriaRepository repository;

    public MateriaServiceImpl(BaseRepository<Materia, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public Materia fingdByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    @Override
    public List<Materia> findByNombreContainingIgnoreCase(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre);
    }

}
