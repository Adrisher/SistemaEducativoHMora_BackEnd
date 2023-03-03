package com.ista.school.service;

import com.ista.school.model.entity.Calificacion;
import com.ista.school.model.entity.Detalle;
import com.ista.school.model.entity.Estudiante;
import com.ista.school.model.entity.Matricula;
import com.ista.school.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalificacionServiceImpl extends BaseServiceImpl<Calificacion, Long> implements CalificacionService {

    @Autowired
    private CalificacionRepository repository;
    @Autowired
    private MatriculaRepository matriculaRepository;
    @Autowired
    private ParcialRepository parcialRepository;
    @Autowired
    private EstudianteRepository estudianteRepository;

    public CalificacionServiceImpl(BaseRepository<Calificacion, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public void agregarCalificacion(Long id_estudiante, Long id_parcial, Calificacion c) {
        Optional<Estudiante> Oestudiante = estudianteRepository.findById(id_estudiante);
        Estudiante estudiante = Oestudiante.get();
        Matricula matricula = matriculaRepository.findByEstudiante(estudiante);
        if (matricula == null) {
            throw new EntityNotFoundException("No existe datos");
        }
        Detalle detalle = matricula.getDetalles().stream()
                .filter(d -> d.getQuimestre().stream()
                        .flatMap(q -> q.getParciales().stream())
                        .anyMatch(p -> p.getId_parcial().equals(id_parcial)))
                .findFirst()
                .orElse(null);

    }
}
