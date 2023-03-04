package com.ista.school.service;

import com.ista.school.model.entity.Curso;
import com.ista.school.model.entity.Estudiante;
import com.ista.school.model.entity.Matricula;
import com.ista.school.repository.BaseRepository;
import com.ista.school.repository.CursoRepository;
import com.ista.school.repository.EstudianteRepository;
import com.ista.school.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatriculaServiceImpl extends BaseServiceImpl<Matricula, Long> implements MatriculaService {

    @Autowired
    private MatriculaRepository repository;
    @Autowired
    private EstudianteRepository eRepository;
    @Autowired
    private CursoRepository cReposity;

    public MatriculaServiceImpl(BaseRepository<Matricula, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public Matricula buscarPorEstudiante(Long id) {
        Optional<Estudiante> e=eRepository.findById(id);
        Estudiante es=e.get();
        return  repository.findByEstudiante(es);
    }

    @Override
    public Matricula buscarPorEstudianteCurso(Long idEst, Long id_curso) {
        Optional<Estudiante> oEst=eRepository.findById(idEst);
        Optional<Curso> oCur=cReposity.findById(id_curso);
        return repository.findByEstudianteAndCurso(oEst.get(),oCur.get());
    }
}
