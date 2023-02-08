package com.ista.backend.controller;

import com.ista.backend.persistence.entity.Matricula;
import com.ista.backend.service.MatriculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hmora/estudiante")
public class MatriculaController {

    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @PostMapping("/Matricular")
    public ResponseEntity<?> matricular(@RequestBody Matricula matricula){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.matriculaService.guardar(matricula));
    }
}
