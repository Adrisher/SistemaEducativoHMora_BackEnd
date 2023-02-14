package com.ista.backend.controller;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Curso;
import com.ista.backend.persistence.entity.Profesor;
import com.ista.backend.persistence.enums.CicloStatus;
import com.ista.backend.persistence.enums.ParaleloStatus;
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

    @PostMapping("/crearCurso/{cicloStatus}/{paraleloStatus}")
    public ResponseEntity<?> crearCurso(@PathVariable("cicloStatus") CicloStatus cicloStatus,
                                        @PathVariable("paraleloStatus")ParaleloStatus paraleloStatus){
        Optional<Curso> oCurso=this.cursoService.buscarPorCicloParalelo(cicloStatus, paraleloStatus);
        if (!oCurso.isPresent()){
            Curso curso=new Curso();
            curso.setCiclo(cicloStatus);
            curso.setParalelo(paraleloStatus);
            return ResponseEntity.status(HttpStatus.CREATED).body(this.cursoService.guardar(curso));
        }
        throw new SistemaEducativoExceptions("Error el curso y paralelo ya existe",HttpStatus.NOT_ACCEPTABLE);

    }

    @GetMapping("/listarCursos")
    public List<Curso> lsitarTodo(){
        List<Curso> cursos= StreamSupport.stream(this.cursoService.listarTodo().spliterator(),false).collect(Collectors.toList());
        return cursos;
    }

    @GetMapping("listarPorCicloStatus/{ciclo}/{paralelo}")
    public ResponseEntity<?> buscar(@PathVariable("ciclo")CicloStatus ciclo,
                                    @PathVariable("paralelo")ParaleloStatus paralelo){
        Optional<Curso> oCurso=this.cursoService.buscarPorCicloParalelo(ciclo, paralelo);
        if (!oCurso.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oCurso);
    }


}
