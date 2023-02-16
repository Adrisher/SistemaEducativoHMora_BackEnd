package com.ista.backend.service;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Asistencia;
import com.ista.backend.persistence.entity.Estudiante;
import com.ista.backend.persistence.enums.AsistenciaStatus;
import com.ista.backend.persistence.repository.AsistenciaRepository;
import com.ista.backend.persistence.repository.EstudianteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaServiceImpl implements AsistenciaService{

    private final AsistenciaRepository asistenciaRepository;
    private final EstudianteRepository estudianteRepository;

    public AsistenciaServiceImpl(AsistenciaRepository asistenciaRepository, EstudianteRepository estudianteRepository) {
        this.asistenciaRepository = asistenciaRepository;
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public Iterable<Asistencia> listarTodo() {
        return this.asistenciaRepository.findAll();
    }

    @Override
    public Page<Asistencia> listarTodo(Pageable pageable) {
        return this.asistenciaRepository.findAll(pageable);
    }

    @Override
    public Optional<Asistencia> buscarPorId(Long id) {
        Optional<Asistencia> optional=this.asistenciaRepository.findById(id);
        if (optional.isEmpty()){
            throw new SistemaEducativoExceptions("asistencia no encontrada", HttpStatus.NOT_FOUND);
        }
        return this.asistenciaRepository.findById(id);
    }

    @Override
    public List<Asistencia> listarPorAsistencia(AsistenciaStatus status) {
        return this.asistenciaRepository.findAllByAsitencia(status);
    }

    @Override
    public Asistencia guardar(Asistencia asistencia) {
        return this.asistenciaRepository.save(asistencia);
    }

    @Override
    public void borrarPorId(Long id) {
        Optional<Asistencia> optional=this.asistenciaRepository.findById(id);
        if (optional.isEmpty()){
            throw new SistemaEducativoExceptions("asistencia no encontrada", HttpStatus.NOT_FOUND);
        }
        this.asistenciaRepository.deleteById(id);
    }

    @Override
    public Optional<Asistencia> bucarPorEstudianteFecha(Date fecha,Estudiante estudiante){
        return this.asistenciaRepository.findByFechaAndEstudiante(fecha,estudiante);
    }
    @Override
    public Optional<Asistencia> buscarPorCedulaFecha(String cedula, Date fecha) {
        Optional<Estudiante> oEstudiante=this.estudianteRepository.findByCedula(cedula);
        Optional<Asistencia> optional=this.asistenciaRepository.findByEstudianteAndFecha(oEstudiante.get(),fecha);
        if (optional.isEmpty()){
            throw new SistemaEducativoExceptions("asistencia no encontrada", HttpStatus.NOT_FOUND);
        }
        return this.asistenciaRepository.findByEstudianteAndFecha(oEstudiante.get(),fecha);
    }

    @Override
    public List<Asistencia> listarPorAlumno(String cedula) {
        Optional<Estudiante> oEstudiante=this.estudianteRepository.findByCedula(cedula);
        if (oEstudiante.isEmpty()){
            throw new SistemaEducativoExceptions("estudiante no encontrado",HttpStatus.NOT_FOUND);
        }
        return this.asistenciaRepository.findAllByEstudiante(oEstudiante.get());
    }
}
