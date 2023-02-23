package com.ista.backend.controller;

import com.ista.backend.persistence.entity.Matricula;
import com.ista.backend.persistence.entity.Periodo;
import com.ista.backend.service.MatriculaService;
import com.ista.backend.service.PeriodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/hmora/matricula")
public class MatriculaController {

    private final MatriculaService matriculaService;
    private final PeriodoService periodoService;

    public MatriculaController(MatriculaService matriculaService, PeriodoService periodoService) {
        this.matriculaService = matriculaService;
        this.periodoService = periodoService;
    }

    @PostMapping("/Matricular")
    public ResponseEntity<?> matricular(@RequestBody Matricula matricula){
        Periodo periodo=new Periodo();
        periodoService.guardar(periodo);
       matricula.setPeriodo(periodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.matriculaService.guardar(matricula));
    }

    @GetMapping("/lista_Matriculas")
    public List<Matricula> listarTodo(){
        List<Matricula> matriculas= StreamSupport.stream(this.matriculaService.listarTodo().spliterator(),false).collect(Collectors.toList());
        return matriculas;
    }



}
