package com.ista.school.service;

import com.ista.school.model.entity.Matricula;
import com.ista.school.repository.BaseRepository;
import com.ista.school.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatriculaServiceImpl extends BaseServiceImpl<Matricula, Long> implements MatriculaService {

    @Autowired
    private MatriculaRepository repository;

    public MatriculaServiceImpl(BaseRepository<Matricula, Long> baseRepository) {
        super(baseRepository);
    }

}
