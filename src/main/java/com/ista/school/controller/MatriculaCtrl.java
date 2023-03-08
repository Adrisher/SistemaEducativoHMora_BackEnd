package com.ista.school.controller;

import com.ista.school.model.entity.Materia;
import com.ista.school.model.entity.Matricula;
import com.ista.school.service.MatriculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hmora/matricula")
@CrossOrigin(origins = {"*"})
public class MatriculaCtrl {

    private final MatriculaService service;

    public MatriculaCtrl(MatriculaService service) {
        this.service = service;
    }


    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Matricula t) {
            return ResponseEntity.ok(service.save(t));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<?>> listar() {
        try {
            return ResponseEntity.ok(service.findAll());
        } catch (Exception e) {
            return (ResponseEntity<List<?>>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @GetMapping("/buscar/{idE}/{idC}")
    private ResponseEntity<?> buscar(@PathVariable Long idE,@PathVariable Long idC){
        Matricula m=service.buscarPorEstudianteCurso(idE, idC);
        return ResponseEntity.ok(m);
    }
}
