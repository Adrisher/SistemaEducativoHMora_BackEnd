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
    public Representante findByCedula(String cedula) {
        return repository.findByCedula(cedula);
    }

}
