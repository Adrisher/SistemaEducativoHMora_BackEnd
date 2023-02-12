package com.ista.backend.controller;

import com.ista.backend.persistence.entity.Curso;
import com.ista.backend.persistence.entity.Profesor;
import com.ista.backend.service.CursoService;
import com.ista.backend.service.ProfesorService;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/hmora/curso")
public class CursoController {

    private final CursoService cursoService;
    private final ProfesorService profesorService;

    public CursoController(CursoService cursoService, ProfesorService profesorService) {
        this.cursoService = cursoService;
        this.profesorService = profesorService;
    }

    @PostMapping("/crearCurso")
    public ResponseEntity<?> crearCurso(@RequestBody Curso curso){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.cursoService.guardar(curso));
    }

    @GetMapping("/listarCursos")
    public List<Curso> lsitarTodo(){
        List<Curso> cursos= StreamSupport.stream(this.cursoService.listarTodo().spliterator(),false).collect(Collectors.toList());
        return cursos;
    }


}