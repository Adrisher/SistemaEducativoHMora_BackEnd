package com.ista.school.controller;

import com.ista.school.model.entity.Curso;
import com.ista.school.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hmora/curso")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CursoCtrl {

    @Autowired
    private CursoService service;

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Curso t) {
        return new ResponseEntity<>(service.save(t), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<?>> listar(String nombre) {
        return new ResponseEntity<>(service.findAll(), HttpStatus.CREATED);
    }

    @PostMapping ("actualizar/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Curso t, @PathVariable Long id) {
        return new ResponseEntity<>(service.update(t, id), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    private RequestEntity<?> eliminar(@PathVariable Long id) {
        return null;
    }

}
