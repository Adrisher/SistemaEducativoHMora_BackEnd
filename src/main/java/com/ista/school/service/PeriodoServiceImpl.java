package com.ista.school.service;

import com.ista.school.model.entity.Periodo;
import com.ista.school.repository.BaseRepository;
import com.ista.school.repository.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeriodoServiceImpl extends BaseServiceImpl<Periodo, Long> implements PeriodoService {

    @Autowired
    private PeriodoRepository repository;

    public PeriodoServiceImpl(BaseRepository<Periodo, Long> baseRepository) {
        super(baseRepository);
    }

}
