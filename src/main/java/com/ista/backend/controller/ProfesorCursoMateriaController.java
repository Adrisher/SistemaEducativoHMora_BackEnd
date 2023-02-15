package com.ista.backend.controller;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Curso;
import com.ista.backend.persistence.entity.Materia;
import com.ista.backend.persistence.entity.Profesor;
import com.ista.backend.persistence.entity.ProfesorCursoMateria;
import com.ista.backend.persistence.enums.CicloStatus;
import com.ista.backend.persistence.enums.MateriaStatus;
import com.ista.backend.persistence.enums.ParaleloStatus;
import com.ista.backend.service.CursoService;
import com.ista.backend.service.MateriaService;
import com.ista.backend.service.ProfesorCursoMateriaService;
import com.ista.backend.service.ProfesorService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/hmora/materiaCursoProfesor")
public class ProfesorCursoMateriaController {

    private final CursoService cursoService;
    private final ProfesorService profesorService;
    private final MateriaService materiaService;
    private final ProfesorCursoMateriaService profesorCursoMateriaService;

    public ProfesorCursoMateriaController(CursoService cursoService, ProfesorService profesorService, MateriaService materiaService, ProfesorCursoMateriaService profesorCursoMateriaService) {
        this.cursoService = cursoService;
        this.profesorService = profesorService;
        this.materiaService = materiaService;
        this.profesorCursoMateriaService = profesorCursoMateriaService;
    }


    @PostMapping("/agregarCursoPorProfesor/{cedulaProfesor}/{cicloStatus}/{paraleloStatus}/{materiaStatus}/curso")
    public ResponseEntity<ProfesorCursoMateria> crearCursoPorProfesor(@PathVariable(value = "cedulaProfesor")String cedulaProfesor,
                                                                      @PathVariable("cicloStatus") CicloStatus cicloStatus,
                                                                      @PathVariable("paraleloStatus") ParaleloStatus paraleloStatus,
                                                                      @PathVariable("materiaStatus")MateriaStatus materiaStatus){


            ProfesorCursoMateria profesorCursoMateria = new ProfesorCursoMateria();

            Optional<Profesor> profesor = this.profesorService.buscarPorCedula(cedulaProfesor);
            if (profesor.isEmpty()) {
                throw new SistemaEducativoExceptions("Profesor no encontrado", HttpStatus.NOT_FOUND);
            }
            Profesor profesor1 = profesor.get();


            Optional<Materia> oMateria = this.materiaService.buscarPorMateria(materiaStatus);
            if (!oMateria.isPresent()) {
                throw new SistemaEducativoExceptions("No existe materia", HttpStatus.NOT_FOUND);
            }
            Materia materia = oMateria.get();

            Optional<Curso> oCurso = this.cursoService.buscarPorCicloParalelo(cicloStatus, paraleloStatus);
            Curso curso = new Curso();
            if (oCurso.isEmpty()) {
                curso.setCiclo(cicloStatus);
                curso.setParalelo(paraleloStatus);
                this.cursoService.guardar(curso);
                    profesorCursoMateria.setCurso(curso);
                    profesorCursoMateria.setMateria(materia);
                    profesorCursoMateria.setProfesor(profesor1);
                    this.profesorCursoMateriaService.guardar(profesorCursoMateria);
                    return new ResponseEntity<>(profesorCursoMateria, HttpStatus.CREATED);

            }
            Optional<ProfesorCursoMateria> oProfesorCursoMateria=this.profesorCursoMateriaService.buscarProfesorCursoMateria(profesor.get(),oCurso.get(),oMateria.get());
            if (oProfesorCursoMateria.isEmpty()){
                curso = oCurso.get();
                profesorCursoMateria.setCurso(curso);
                profesorCursoMateria.setMateria(materia);
                profesorCursoMateria.setProfesor(profesor1);
                this.profesorCursoMateriaService.guardar(profesorCursoMateria);
                return new ResponseEntity<>(profesorCursoMateria, HttpStatus.CREATED);
            }
           throw new SistemaEducativoExceptions("Error",HttpStatus.NOT_FOUND);


    }

   @GetMapping("obtener/{cedulaProfesor}/{cicloStatus}/{paraleloStatus}/{materiaStatus}")
    public ResponseEntity<?> obtener(@PathVariable(value = "cedulaProfesor")String cedulaProfesor,
                                                  @PathVariable("cicloStatus") CicloStatus cicloStatus,
                                                  @PathVariable("paraleloStatus") ParaleloStatus paraleloStatus,
                                                  @PathVariable("materiaStatus")MateriaStatus materiaStatus){
        Optional<Profesor> oProfesor=this.profesorService.buscarPorCedula(cedulaProfesor);
        Optional<Curso> oCurso=this.cursoService.buscarPorCicloParalelo(cicloStatus,paraleloStatus);
        Optional<Materia> oMateria=this.materiaService.buscarPorMateria(materiaStatus);


        if(!oProfesor.isEmpty()&&!oCurso.isEmpty()&&!oMateria.isEmpty()){
            Optional<ProfesorCursoMateria> oProfesorCursoMateria=this.profesorCursoMateriaService.buscarProfesorCursoMateria(oProfesor.get(),oCurso.get(),oMateria.get());
            return ResponseEntity.ok(oProfesorCursoMateria);
        }
       throw new SistemaEducativoExceptions("datos no encontrados",HttpStatus.NOT_FOUND);
   }

   @GetMapping("/listar")
    public List<ProfesorCursoMateria> listarTodo(){
        List<ProfesorCursoMateria> profesorCursoMaterias= StreamSupport.stream(this.profesorCursoMateriaService.listarTodo().spliterator(),false).collect(Collectors.toList());
        return profesorCursoMaterias;
   }

   @PutMapping("")




}
