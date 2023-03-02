package com.ista.school.controller;

import com.ista.school.model.entity.ProfesorCursoMateria;
import com.ista.school.service.ProfesorCursoMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hmora/pmc")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ProfesorCursoMateriaCtrl {

    @Autowired
    private ProfesorCursoMateriaService service;

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

}