package com.ista.school.controller;

import com.ista.school.model.entity.Curso;
import com.ista.school.model.entity.Materia;
import com.ista.school.model.entity.Profesor;
import com.ista.school.model.entity.ProfesorCursoMateria;
import com.ista.school.service.CursoService;
import com.ista.school.service.MateriaService;
import com.ista.school.service.ProfesorCursoMateriaService;
import com.ista.school.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hmora/pmc")
@CrossOrigin(origins = {"*"})
public class ProfesorCursoMateriaCtrl {

    @Autowired
    private ProfesorCursoMateriaService service;
    @Autowired
    CursoService cursoService;
    @Autowired
    ProfesorService profesorService;
    @Autowired
    MateriaService materiaService;


    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody ProfesorCursoMateria t) {
        return new ResponseEntity<>(service.save(t), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<?>> listar() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping ("actualizar/{id}")
    public ResponseEntity<?> actualizar(@RequestBody ProfesorCursoMateria t, @PathVariable Long id) {
        ProfesorCursoMateria current = service.findById(id).orElse(null);
        if (current != null) {
            current.setProfesor(t.getProfesor());
            current.setCurso(t.getCurso());
            current.setMateria(t.getMateria());
            return new ResponseEntity<>(service.save(t), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(" Error del Servidor");
    }

    @DeleteMapping("/eliminar/{id}")
    private ResponseEntity<?> eliminar(@PathVariable Long id) {
        return null;
    }


    @PostMapping("/agregarCursoPorProfesor/{cedulaProfesor}/{cicloStatus}/{paraleloStatus}/{materiaStatus}/curso")
    public ResponseEntity<ProfesorCursoMateria> crearCursoPorProfesor(@PathVariable(value = "cedulaProfesor")String cedulaProfesor,
                                                                      @PathVariable("cicloStatus") String cicloStatus,
                                                                      @PathVariable("paraleloStatus") String paraleloStatus,
                                                                      @PathVariable("materiaStatus")String materiaStatus){


        ProfesorCursoMateria profesorCursoMateria = new ProfesorCursoMateria();

        Profesor profesor = this.profesorService.findByCedula(cedulaProfesor);
        if (profesor==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Profesor profesor1 = profesor;


        Materia oMateria = this.materiaService.fingdByNombre(materiaStatus);
        if (oMateria==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Materia materia = oMateria;

        Curso oCurso = this.cursoService.buscarPorCicloParalelo(cicloStatus, paraleloStatus);
        Curso curso = new Curso();
        if (oCurso==null) {
            curso.setCiclo(cicloStatus);
            curso.setParalelo(paraleloStatus);
            this.cursoService.save(curso);
            profesorCursoMateria.setCurso(curso);
            profesorCursoMateria.setMateria(materia);
            profesorCursoMateria.setProfesor(profesor1);
            this.service.save(profesorCursoMateria);
            return new ResponseEntity<>(profesorCursoMateria, HttpStatus.CREATED);
        }
        ProfesorCursoMateria oProfesorCursoMateria=this.service.buscarProfesorCursoMateria(profesor,oCurso,oMateria);
        if (oProfesorCursoMateria==null){
            curso = oCurso;
            profesorCursoMateria.setCurso(curso);
            profesorCursoMateria.setMateria(materia);
            profesorCursoMateria.setProfesor(profesor1);
            this.service.save(profesorCursoMateria);
            return new ResponseEntity<>(profesorCursoMateria, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findByCurso")
    public List<ProfesorCursoMateria> findByCurso(@RequestParam("idCurso") Long idCurso) {
        return service.findByCurso(idCurso);
    }


}