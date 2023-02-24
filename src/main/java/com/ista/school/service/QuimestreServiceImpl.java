package com.ista.school.service;

import com.ista.school.model.entity.Quimestre;
import com.ista.school.repository.BaseRepository;
import com.ista.school.repository.QuimestreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuimestreServiceImpl extends BaseServiceImpl<Quimestre, Long> implements QuimestreService {

    @Autowired
    private QuimestreRepository repository;

    public QuimestreServiceImpl(BaseRepository<Quimestre, Long> baseRepository) {
        super(baseRepository);
    }

}
