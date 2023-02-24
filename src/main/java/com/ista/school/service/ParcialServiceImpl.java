package com.ista.school.service;

import com.ista.school.model.entity.Parcial;
import com.ista.school.repository.BaseRepository;
import com.ista.school.repository.ParcialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParcialServiceImpl extends BaseServiceImpl<Parcial, Long> implements ParcialService {

    @Autowired
    private ParcialRepository repository;

    public ParcialServiceImpl(BaseRepository<Parcial, Long> baseRepository) {
        super(baseRepository);
    }

}
