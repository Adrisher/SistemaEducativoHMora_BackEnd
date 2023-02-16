package com.ista.backend.controller;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Asistencia;
import com.ista.backend.persistence.entity.Estudiante;
import com.ista.backend.persistence.enums.AsistenciaStatus;
import com.ista.backend.service.AsistenciaService;
import com.ista.backend.service.EstudianteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hmora/asistencia")
public class AsistenciaController {

    private final AsistenciaService asistenciaService;
    private final EstudianteService estudianteService;

    public AsistenciaController(AsistenciaService asistenciaService, EstudianteService estudianteService) {
        this.asistenciaService = asistenciaService;
        this.estudianteService = estudianteService;
    }

    @PostMapping("/AgregarAsistenciaPorEstudiante/{cedula}/estudiantes")
    public ResponseEntity<Asistencia> addAsistencia(@PathVariable("cedula")String cedula,
                                           @RequestBody Asistencia asistencia) {

        Optional<Estudiante> optionalEstudiante=this.estudianteService.buscarPorCedula(cedula);
        if (!optionalEstudiante.isPresent()){
            throw new SistemaEducativoExceptions("Alumno no registrado",HttpStatus.NOT_FOUND);
        }
        Optional<Asistencia> oAsistencia=this.asistenciaService.bucarPorEstudianteFecha(asistencia.getFecha(),optionalEstudiante.get());
        if (!oAsistencia.isPresent()){
            Asistencia asist = this.estudianteService.buscarPorCedula(cedula).map(estudiante -> {
                asistencia.setEstudiante(estudiante);
                return this.asistenciaService.guardar(asistencia);
            }).orElseThrow();
            return new ResponseEntity<>(asist, HttpStatus.CREATED);
        }
        throw new SistemaEducativoExceptions("fecha ya fue asignada",HttpStatus.FOUND);


    }

    @GetMapping("/ListarAsistenciaPorEstudiante/{cedula}")
    public List<Asistencia> listarPorAlumno(@PathVariable("cedula")String cedula){
        return this.asistenciaService.listarPorAlumno(cedula);
    }

    @GetMapping("/buscarPorAlumnoFecha/{cedula}")
    public ResponseEntity<?> buscarAlumnoFecha(@PathVariable("cedula")String cedula,
                                               @RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha){
        Optional<Asistencia> oAsistencia=this.asistenciaService.buscarPorCedulaFecha(cedula, fecha);
        if (!oAsistencia.isPresent()){
            throw new SistemaEducativoExceptions("Datos no encontrados",HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(oAsistencia);
    }

    @PutMapping("/ActualizarAsistencia/{cedula}")
    public ResponseEntity<?> actualizarAsistencia(@PathVariable("cedula")String cedula,
                                                  @RequestParam("fecha")@DateTimeFormat(pattern="yyyy-MM-dd")Date fecha){
        Optional<Asistencia> oAsistencia=this.asistenciaService.buscarPorCedulaFecha(cedula, fecha);
        if (!oAsistencia.isPresent()){
            throw new SistemaEducativoExceptions("Datos no encontrados",HttpStatus.NOT_FOUND);
        }
        oAsistencia.get().setFaltas(0);
        oAsistencia.get().setAsitencia(AsistenciaStatus.JUSTIFICADO);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.asistenciaService.guardar(oAsistencia.get()));
    }

}
