package com.ista.school.controller;

import com.ista.school.model.entity.Estudiante;
import com.ista.school.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hmora/estudiante")
@CrossOrigin(origins = {"http://localhost:4200"})
public class EstudianteCtrl {

    @Autowired
    private EstudianteService service;

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Estudiante t) {
        Estudiante esxiste = service.findByCedula(t.getCedula());
        if (esxiste == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nombre existente!!");
        } else {
            return new ResponseEntity<>(service.save(t), HttpStatus.CREATED);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<?>> listar(String nombre) {
        if (!nombre.trim().isEmpty()) {
            try {
                List<Estudiante> materias = this.service.findAll();
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
    public ResponseEntity<?> actualizar(@RequestBody Estudiante t, @PathVariable Long id) {
        try{
            Estudiante current = service.findById(id).orElse(null);
            if (current == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Materia no existente");
            } else if (service.findByCedula(t.getNombre()) == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Materia no existente");
            } else {
                return new ResponseEntity<>(service.update(t, id), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    private RequestEntity<?> eliminar(@PathVariable Long id) {
        return null;
    }

}
