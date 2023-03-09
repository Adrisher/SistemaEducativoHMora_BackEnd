package com.ista.school.controller;

import com.ista.school.model.entity.*;
import com.ista.school.service.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private MatriculaService matriculaService;
    @Autowired
    private QuimestreService quimestreService; 
    @Autowired
    private DetalleService detalleService;
    @Autowired
    private EstudianteService repository;

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
            return new ArrayList<>();
        }
        List<Matricula> matriculas=curso.getMatriculas();
        for (Matricula matricula:matriculas){
            System.out.println(matricula.getCurso().getIdCurso());
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
        return new ArrayList<>();



    }

    @GetMapping("/listarQuiemestre/{id_est}/{id_materia}/{id_curso}")
    public List<Quimestre> listarQuimestres(@PathVariable Long id_est,@PathVariable Long id_materia,@PathVariable Long id_curso){

        Matricula matricula=matriculaService.buscarPorEstudianteCurso(id_est, id_curso);
        List<Quimestre> lista=new ArrayList<>();

        if (matricula==null){
            return new ArrayList<>();
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

        if (matricula==null){
            return new ArrayList<>();
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
            return new ArrayList<>();
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


    @PostMapping("/addCalifi/{id_est}/{id_parcial}/{promedio}")
    public ResponseEntity<?> addCalif(@PathVariable Long id_est,@PathVariable Long id_parcial,@PathVariable String promedio, @RequestBody Calificacion c){
        Optional<Calificacion> find = calificaion.findById(c.getId_calificacion());

        Matricula matricula=matriculaService.buscarPorEstudiante(id_est);

        Double prome = Double.parseDouble(promedio);

        if (!find.isEmpty()) {
            System.out.println("encontrado");
            find.get().setCalificacion(c.getCalificacion());
            find.get().setDescripcion(c.getDescripcion());
            return new ResponseEntity<>(calificaion.save(find.get()), HttpStatus.OK);
        }else{
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
                            Optional<Parcial> oParcial=parcialService.findById(id_parcial);
                            c.setParcial(oParcial.get());
                            oParcial.get().setPromedio(prome);
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
  
    @PostMapping("/addQuiemestre/{id_est}/{id_materia}/{id_curso}")
    public ResponseEntity<?> addQuimestres(@PathVariable Long id_est,@PathVariable Long id_materia,@PathVariable Long id_curso,@RequestBody Quimestre q){

        Matricula matricula=matriculaService.buscarPorEstudianteCurso(id_est, id_curso);
        System.out.println(matricula.getId_matricula());    
        

        if (matricula==null){
            return ResponseEntity.notFound().build();
        }

        List<Detalle> detalles = matricula.getDetalles();
        for (Detalle detalle:detalles){
            if (detalle.getMateria().getId_materia().equals(id_materia)){
                Optional<Detalle> find=detalleService.findById(detalle.getId_detalle());
                System.out.println(detalle.getId_detalle());
                System.out.println(find.get().getId_detalle());
                q.setDetalle(find.get());
                quimestreService.save(q);
                detalleService.save(find.get());
                return ResponseEntity.ok(q);
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
        
    }

    @PostMapping("/addParciales/{id_est}/{id_quim}")
    public ResponseEntity<?> addParciales(@PathVariable Long id_est,@PathVariable Long id_quim,@RequestBody Parcial p) {
        Matricula matricula = matriculaService.buscarPorEstudiante(id_est);
        System.out.println(matricula.getId_matricula());

        if (matricula==null){
            throw new EntityNotFoundException("No se encontró la matrícula del estudiante con ID " + id_est);
        }

        List<Detalle> detalles = matricula.getDetalles();
        for (Detalle detalle : detalles) {
        	System.out.println(detalle.getId_detalle());
            List<Quimestre> quimestres = detalle.getQuimestre();
            for (Quimestre quimestre : quimestres) {
                if (quimestre.getId_quimestre().equals(id_quim)){
                        Optional<Quimestre> oQuim=quimestreService.findById(id_quim);
                        p.setQuimestre(oQuim.get());
                        parcialService.save(p);
                        quimestreService.save(oQuim.get());
                        return ResponseEntity.ok(p);
                }
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/buscarEstudiante/{cedula}")
    public ResponseEntity<?> buscraEst(@PathVariable String cedula){

        Estudiante est=repository.findByCedula(cedula);
        return ResponseEntity.ok(est);

    }

    @PutMapping("/actualizarQuimestre/{prome}/{examen}")
    public ResponseEntity<?> updateQuiemstre(@PathVariable String examen,@PathVariable String prome,@RequestBody Quimestre quim){

        Double ex = Double.parseDouble(examen);
        Double prom=Double.parseDouble(prome);
        Optional<Quimestre> oQuim=quimestreService.findById(quim.getId_quimestre());
        Quimestre quimestre=oQuim.get();
        if(quimestre==null){
            throw new EntityNotFoundException("No se encontró el quimestre");
        }
        quimestre.setExamen_quimestral(ex);
        Optional<Detalle> oDetalle=detalleService.findById(quimestre.getDetalle().getId_detalle());
        quimestre.getDetalle().setPromedio_final(prom);
        detalleService.save(oDetalle.get());
        quimestreService.save(quimestre);
        return ResponseEntity.ok(quimestre);
    }

    @PutMapping("/actualizarParcial/{prueba}/{promedio}")
    public ResponseEntity<?> updateParcial(@PathVariable String prueba,@PathVariable String promedio,@RequestBody Parcial parc){

        Double prue = Double.parseDouble(prueba);
        Double prom = Double.parseDouble(promedio);

        Optional<Parcial> oParcial=parcialService.findById(parc.getId_parcial());
        Parcial parcial=oParcial.get();
        if(parcial==null){
            throw new EntityNotFoundException("No se encontró el parcial");
        }
        parcial.setPrueba_parcial(prue);
        Optional<Quimestre> quimestre=quimestreService.findById(parcial.getQuimestre().getId_quimestre());
        parcial.getQuimestre().setPromedioParcial(prom);
        quimestreService.save(quimestre.get());
        parcialService.save(parcial);

        return ResponseEntity.ok(quimestre.get());


    }


}