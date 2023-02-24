package com.ista.school.service;

import com.ista.school.model.entity.Detalle;
import com.ista.school.repository.BaseRepository;
import com.ista.school.repository.DetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DetalleServiceImpl extends BaseServiceImpl<Detalle, Long> implements DetalleService {

    @Autowired
    private DetalleRepository repository;

    public DetalleServiceImpl(BaseRepository<Detalle, Long> baseRepository) {
        super(baseRepository);
    }

}
