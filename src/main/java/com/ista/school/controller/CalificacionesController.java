package com.ista.school.controller;

import com.ista.school.model.entity.*;
import com.ista.school.service.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hmora/calificaciones")
@CrossOrigin(origins = {"*"})
public class CalificacionesController {

    @Autowired
    private CalificacionService calificaion;
    @Autowired
    private CursoService cursoService;
    @Autowired
    private ParcialService parcialService;
    @Autowired
    private QuimestreService quimestreService;
    @Autowired
    private DetalleService detalleService;
    @Autowired
    private MateriaService materiaService;
    @Autowired
    private MatriculaService matriculaService;


    @PostMapping("/crearActualizar")
    public ResponseEntity<?> crearActualizar(@RequestBody Calificacion c) {
        System.out.println("ok");

        try {
            Optional<Calificacion> find = calificaion.findById(c.getId_calificacion());
            if (!find.isEmpty()) {
                System.out.println("encontrado");
                find.get().setCalificacion(c.getCalificacion());
                find.get().setDescripcion(c.getDescripcion());
                return new ResponseEntity<>(calificaion.save(find.get()), HttpStatus.OK);
            }
            System.out.println("creando");
            return new ResponseEntity<>(calificaion.save(c), HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (UnexpectedTypeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage() + " Revisar los campos");
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<?>> listar(){
        try {
            return new ResponseEntity<>(calificaion.findAll(),HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonList(e.getMessage()+"Revisar los campos"));
        }
    }

    @GetMapping("/returnidCurso/{idCurso}/{paralelo}")
    public ResponseEntity<Curso> returnId(@PathVariable String idCurso, @PathVariable String paralelo){
        Curso curso=cursoService.buscarPorCicloParalelo(idCurso,paralelo);
        System.out.println("OK");
        return ResponseEntity.ok(curso);
    }

    @GetMapping("/ListarEstudiantesCurso/{idCurso}/{paralelo}")
    public List<Estudiante> ListarEstudiantesPorCurso(@PathVariable String idCurso, @PathVariable String paralelo){
        List<Estudiante> estudiantes=new ArrayList<>();
        Curso curso=cursoService.buscarPorCicloParalelo(idCurso,paralelo);

        if (curso==null){
            throw new EntityNotFoundException("no existe");
        }
        List<Matricula> matriculas=curso.getMatriculas();
        for (Matricula matricula:matriculas){
            System.out.println(matricula.getCurso().getId_curso());
            estudiantes.add(matricula.getEstudiante());
        }
        return estudiantes;
    }


    @GetMapping("/listarmaterias/{id_estudiante}/{id_curso}")
    public List<Materia> listarMaterias(@PathVariable Long id_estudiante, @PathVariable Long id_curso){

        Matricula matricula=matriculaService.buscarPorEstudianteCurso(id_estudiante, id_curso);
        List<Materia> materias=new ArrayList<>();

        if (matricula!=null){
            List<Detalle> detalles = matricula.getDetalles();
            if (detalles.isEmpty()){
                throw new RuntimeException("lista vacia");
            }
            for (Detalle detalle:detalles){

                materias.add(detalle.getMateria());
            }

            if (materias.isEmpty()){
                throw new RuntimeException("lista vacia ");
            }
            return materias;
        }
        throw new EntityNotFoundException("ERROR");



    }

    @GetMapping("/listarQuiemestre/{id_est}/{id_materia}/{id_curso}")
    public List<Quimestre> listarQuimestres(@PathVariable Long id_est,@PathVariable Long id_materia,@PathVariable Long id_curso){

        Matricula matricula=matriculaService.buscarPorEstudianteCurso(id_est, id_curso);
        List<Quimestre> lista=new ArrayList<>();

        if (matricula==null){
            throw new EntityNotFoundException("No se encontró la matrícula del estudiante con ID " + id_est);
        }

        List<Detalle> detalles = matricula.getDetalles();
        for (Detalle detalle:detalles){
            if (detalle.getMateria().getId_materia().equals(id_materia)){
                System.out.println(detalle.getId_detalle());
                List<Quimestre> quimestres=detalle.getQuimestre();
                for (Quimestre quimestre:quimestres){
                    lista.add(quimestre);
                    System.out.println(quimestre.getId_quimestre());
                }
            }


        }
        return lista;
    }

    @GetMapping("/listarParciales/{id_est}/{id_quim}")
    public List<Parcial> listarParciales(@PathVariable Long id_est,@PathVariable Long id_quim) {
        Matricula matricula = matriculaService.buscarPorEstudiante(id_est);
        System.out.println(matricula.getId_matricula());
        List<Parcial> listas=new ArrayList<>();

        if (matricula == null) {
            throw new EntityNotFoundException("No se encontró la matrícula del estudiante con ID " + id_est);
        }

        List<Detalle> detalles = matricula.getDetalles();
        for (Detalle detalle : detalles) {
            List<Quimestre> quimestres = detalle.getQuimestre();
            for (Quimestre quimestre : quimestres) {
                if (quimestre.getId_quimestre().equals(id_quim)){
                    List<Parcial> parciales = quimestre.getParciales();
                    for (Parcial parcial:parciales){
                        System.out.println(parcial.getId_parcial());
                        listas.add(parcial);
                    }
                }



            }
        }
        return listas;
    }

    @GetMapping("/listarCalifiaciones/{id_est}/{id_parcial}")
    public List<Calificacion> listarCalificaciones(@PathVariable Long id_est,@PathVariable Long id_parcial){
        Matricula matricula=matriculaService.buscarPorEstudiante(id_est);
        System.out.println(matricula.getId_matricula());
        List<Calificacion> lista=new ArrayList<>();

        if (matricula==null){
            throw new EntityNotFoundException("No se encontró la matrícula del estudiante con ID " + id_est);
        }

        List<Detalle> detalles = matricula.getDetalles();
        for (Detalle detalle:detalles){
            List<Quimestre> quimestres=detalle.getQuimestre();
            for (Quimestre quimestre:quimestres){
                List<Parcial> parciales=quimestre.getParciales();
                for (Parcial parcial:parciales){
                    System.out.println(parcial.getId_parcial());
                    if (parcial.getId_parcial().equals(id_parcial)){
                        List<Calificacion> calificaciones=parcial.getCalificaciones();
                        for (Calificacion calificacion:calificaciones){
                            lista.add(calificacion);
                        }
                    }
                }
            }
        }
        return lista;
    }

    @PostMapping("/adddCalificacionEstudiante/{idEstudiante}/{id_parcial}")
    public ResponseEntity<?> agregarcalificacion(@PathVariable Long idEstudiante,@PathVariable Long id_parcial, @RequestBody Calificacion c){

        Matricula matricula=matriculaService.buscarPorEstudiante(idEstudiante);
        System.out.println(matricula.getId_matricula());



        if (matricula==null){
            throw new EntityNotFoundException("No se encontró la matrícula del estudiante con ID " + idEstudiante);
        }

        List<Detalle> detalles = matricula.getDetalles();
        for (Detalle detalle:detalles){
            List<Quimestre> quimestres=detalle.getQuimestre();
            for (Quimestre quimestre:quimestres){
                List<Parcial> parciales=quimestre.getParciales();
                for (Parcial parcial:parciales){
                    System.out.println(parcial.getId_parcial());
                    if (parcial.getId_parcial().equals(id_parcial)){
                        Optional<Parcial> oParcial=parcialService.findById(id_parcial);
                        c.setParcial(oParcial.get());
                        calificaion.save(c);
                        parcialService.save(oParcial.get());
                        return ResponseEntity.ok(parcial);
                    }
                }
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}