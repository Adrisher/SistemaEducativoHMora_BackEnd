package com.ista.school.controller;

import com.ista.school.model.entity.Representante;
import com.ista.school.service.RepresentanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hmora/representante")
@CrossOrigin(origins = {"http://localhost:4200"})
public class RepresentanteCtrl {

    @Autowired
    private RepresentanteService service;

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Representante t) {
        try {
            return new ResponseEntity<>(service.save(t), HttpStatus.CREATED);
        } catch (Exception e) {

        }
        return null;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<?>> listar() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping ("actualizar/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Representante materia, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.update(materia, id), HttpStatus.OK);
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
