package com.ista.backend.service;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Curso;
import com.ista.backend.persistence.enums.CicloStatus;
import com.ista.backend.persistence.enums.ParaleloStatus;
import com.ista.backend.persistence.repository.CursoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService{

    private final CursoRepository cursoRepository;

    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }


    @Override
    public Iterable<Curso> listarTodo() {
        return this.cursoRepository.findAll();
    }

    @Override
    public Page<Curso> listarTodo(Pageable pageable) {
        return this.cursoRepository.findAll(pageable);
    }

    @Override
    public Optional<Curso> buscarPorId(Long id) {
        return this.cursoRepository.findById(id);
    }

    @Override
    public List<Curso> listarPorParalelo(ParaleloStatus status) {
        return this.cursoRepository.findAllByParalelo(status);
    }

    @Override
    public Curso guardar(Curso curso) {
        return this.cursoRepository.save(curso);
    }

    @Override
    public void borrarPorId(Long id) {

        Optional<Curso> optional=this.cursoRepository.findById(id);
        if (optional.isEmpty()){
            throw new SistemaEducativoExceptions("curso no encontrada", HttpStatus.NOT_FOUND);
        }
        this.cursoRepository.deleteById(id);
    }

    @Override
    public Optional<Curso> buscarPorCiclo(String status) {
        return this.cursoRepository.findByCiclo(status);
    }

    @Override
    public Optional<Curso> buscarPorParalelo(String status) {
        return this.cursoRepository.findByParalelo(status);
    }

    @Override
    public Optional<Curso> buscarPorCicloParalelo(String cicloStatus, String paralelo) {
        return this.cursoRepository.findByCicloAndParalelo(cicloStatus,paralelo);
    }
}
