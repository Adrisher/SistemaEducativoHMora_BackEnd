package com.ista.school.controller;

import com.ista.school.model.entity.Profesor;
import com.ista.school.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hmora/profesor")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ProfesorCtrl {

    @Autowired
    private ProfesorService service;

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Profesor t){
        return new ResponseEntity<>(service.save(t), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<?>> listar(String nombre) {
        if (!nombre.trim().isEmpty()) {
            try {
                List<Profesor> materias = this.service.findAll();
                return new ResponseEntity<>(materias, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            try {
                return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping ("actualizar/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Profesor t, @PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/eliminar/{id}")
    private RequestEntity<?> eliminar(@PathVariable Long id) {
        return null;
    }


}
