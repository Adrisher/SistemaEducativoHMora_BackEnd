package com.ista.backend.service;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Materia_Curso_Profesor;
import com.ista.backend.persistence.repository.MateriaCursoProfesorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MateriaCursoProfesorServiceImpl implements MateriaCursoProfesorService{

    private final MateriaCursoProfesorRepository materiaCursoProfesorRepository;

    public MateriaCursoProfesorServiceImpl(MateriaCursoProfesorRepository materiaCursoProfesorRepository) {
        this.materiaCursoProfesorRepository = materiaCursoProfesorRepository;
    }

    @Override
    public Iterable<Materia_Curso_Profesor> listarTodo() {
        return this.materiaCursoProfesorRepository.findAll();
    }

    @Override
    public Page<Materia_Curso_Profesor> listarTodo(Pageable pageable) {
        return this.materiaCursoProfesorRepository.findAll(pageable);
    }

    @Override
    public Optional<Materia_Curso_Profesor> buscarPorId(Long id) {
        return this.materiaCursoProfesorRepository.findById(id);
    }

    @Override
    public Materia_Curso_Profesor guardar(Materia_Curso_Profesor materiaCursoProfesor) {
        return this.materiaCursoProfesorRepository.save(materiaCursoProfesor);
    }

    @Override
    public void borrarPorId(Long id) {

        Optional<Materia_Curso_Profesor> optional=this.materiaCursoProfesorRepository.findById(id);
        if (optional.isEmpty()){
            throw new SistemaEducativoExceptions("parametro no encontrado", HttpStatus.NOT_FOUND);
        }
        this.materiaCursoProfesorRepository.deleteById(id);

    }
}
