package com.ista.school.service;

import com.ista.school.model.entity.Curso;
import com.ista.school.repository.BaseRepository;
import com.ista.school.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl extends BaseServiceImpl<Curso, Long> implements CursoService {

    @Autowired
    private CursoRepository repository;

    public CursoServiceImpl(BaseRepository<Curso, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public List<Curso> findByCicloOrParalelo(String filtro) {
        return repository.findByCicloOrParalelo(filtro);
    }

    @Override
    public boolean exist(String ciclo, String paralelo) {
        return repository.exist(ciclo, paralelo);
    }

}
