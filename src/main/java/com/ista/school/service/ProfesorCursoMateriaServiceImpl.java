package com.ista.school.service;

import com.ista.school.model.entity.ProfesorCursoMateria;
import com.ista.school.repository.BaseRepository;
import com.ista.school.repository.ProfesorCursoMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorCursoMateriaServiceImpl extends BaseServiceImpl<ProfesorCursoMateria, Long> implements ProfesorCursoMateriaService {

    @Autowired
    private ProfesorCursoMateriaRepository repository;

    public ProfesorCursoMateriaServiceImpl(BaseRepository<ProfesorCursoMateria, Long> baseRepository) {
        super(baseRepository);
    }

}
