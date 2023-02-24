package com.ista.school.service;

import com.ista.school.model.entity.Asistencia;
import com.ista.school.repository.AsistenciaRepository;
import com.ista.school.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsistenciaServiceImpl extends BaseServiceImpl<Asistencia, Long> implements AsistenciaService {

    @Autowired
    private AsistenciaRepository repository;

    public AsistenciaServiceImpl(BaseRepository<Asistencia, Long> baseRepository) {
        super(baseRepository);
    }

}
