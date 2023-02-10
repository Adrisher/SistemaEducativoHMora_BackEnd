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

    @PostMapping("/CrearCursoPorProfesor/{id_profesor}/cursos")
    public ResponseEntity<Curso> crearCurso(@PathVariable(value = "id_profesor")Long id_profesor,
                                            @RequestBody Curso curso){
        Curso curso1=this.profesorService.buscarPorId(id_profesor).map(profesor ->{
            curso.setProfesor(profesor);
            return this.cursoService.guardar(curso);
        }).orElseThrow(()-> new OpenApiResourceNotFoundException("id no existente "));
        return new ResponseEntity<>(curso1,HttpStatus.CREATED);
    }

    @GetMapping("/ListarCursoPorProfesor/{id_profesor}/curso")
    public ResponseEntity<List<Curso>> listarCursoPorProfesor(@PathVariable(value= "id_profesor")Long id_profesor){
        if(!this.profesorService.existsById(id_profesor)){
            throw new OpenApiResourceNotFoundException("no se encontro");
        }
        Optional<Profesor> oProfesor=this.profesorService.buscarPorId(id_profesor);

        List<Curso>  cursos=oProfesor.get().getCurso();
        return new ResponseEntity<>(cursos,HttpStatus.OK);
    }

}
