package com.ista.school.controller;

import com.ista.school.model.entity.Materia;
import com.ista.school.service.MateriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hmora/materia")
@CrossOrigin(origins = {"http://localhost:4200"})
public class MateriaCtrl {

    private final MateriaService service;

    public MateriaCtrl(MateriaService service) {
        this.service = service;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Materia t) {
        Materia esxiste = service.fingdByNombre(t.getNombre());
        if (esxiste != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Materia existente!!");
        } else if (!t.getNombre().isEmpty() && !t.getDescripcion().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Se deben llenar todos los campos!!");
        } else {
            return new ResponseEntity<>(service.save(t), HttpStatus.CREATED);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<?>> listar(String nombre) {
        if (!nombre.trim().isEmpty()) {
            try {
                List<Materia> materias = this.service.findAll();
                return new ResponseEntity<>(materias, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            try {
                return new ResponseEntity<>(service.findByNombreContainingIgnoreCase(nombre), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping ("actualizar/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Materia t, @PathVariable Long id) {
        try{
            Materia current = service.findById(id).orElse(null);
            if (current == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Materia no existente");
            } else if (service.fingdByNombre(t.getNombre()) == null) {
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
