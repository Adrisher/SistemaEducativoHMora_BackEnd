package com.ista.school.service;

import com.ista.school.model.entity.Curso;
import com.ista.school.model.entity.Materia;
import com.ista.school.model.entity.Profesor;
import com.ista.school.model.entity.ProfesorCursoMateria;
import com.ista.school.repository.BaseRepository;
import com.ista.school.repository.ProfesorCursoMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorCursoMateriaServiceImpl extends BaseServiceImpl<ProfesorCursoMateria, Long> implements ProfesorCursoMateriaService {

    @Autowired
    private ProfesorCursoMateriaRepository repository;

    public ProfesorCursoMateriaServiceImpl(BaseRepository<ProfesorCursoMateria, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public ProfesorCursoMateria buscarProfesorCursoMateria(Profesor profesor, Curso curso, Materia materia) {
        return this.repository.findByProfesorAndCursoAndMateria(profesor,curso,materia);
    }

    @Override
    public List<ProfesorCursoMateria> findByCurso(Long idCurso) {
        return repository.findByCursoIdCurso(idCurso);
    }

}
