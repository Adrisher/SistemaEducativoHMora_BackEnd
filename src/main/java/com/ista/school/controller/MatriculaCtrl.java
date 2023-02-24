package com.ista.school.controller;

import com.ista.school.model.entity.Materia;
import com.ista.school.model.entity.Matricula;
import com.ista.school.service.MatriculaService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hmora/matricula")
@CrossOrigin(origins = {"http://localhost:4200"})
public class MatriculaCtrl {

    private final MatriculaService service;

    public MatriculaCtrl(MatriculaService service) {
        this.service = service;
    }


    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Matricula t) {
        try {

        } catch (Exception e) {

        }
        return null;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<?>> listar(String nombre) {
        try {

        } catch (Exception e) {

        }
        return null;
    }

    @PostMapping ("actualizar/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Materia materia, @PathVariable Long id) {
        try {

        } catch (Exception e) {

        }
        return null;
    }

    @DeleteMapping("/eliminar/{id}")
    private RequestEntity<?> eliminar(@PathVariable Long id) {
        try {

        } catch (Exception e) {

        }
        return null;
    }

}
