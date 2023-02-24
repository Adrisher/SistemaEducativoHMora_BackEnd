package com.ista.school.service;

import com.ista.school.model.entity.Calificacion;
import com.ista.school.repository.AsistenciaRepository;
import com.ista.school.repository.BaseRepository;
import com.ista.school.repository.CalificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalificacionServiceImpl extends BaseServiceImpl<Calificacion, Long> implements CalificacionService {

    @Autowired
    private CalificacionRepository repository;

    public CalificacionServiceImpl(BaseRepository<Calificacion, Long> baseRepository) {
        super(baseRepository);
    }

}
