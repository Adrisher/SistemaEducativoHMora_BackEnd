package com.ista.school.controller;

import com.ista.school.model.entity.Materia;
import com.ista.school.service.MateriaService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hmora/materia")
@CrossOrigin(origins = {"*"})
public class MateriaCtrl {

    private final MateriaService service;

    public MateriaCtrl(MateriaService service) {
        this.service = service;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Materia t) {
        try {
            return new ResponseEntity<>(service.save(t), HttpStatus.OK);
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getConstraintViolations());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/buscar/")
    public ResponseEntity<List<?>> buscar(@RequestParam("nombre") String nombre) {
        try {
            if (nombre.trim().isEmpty()) {
                List<Materia> materias = this.service.findAll();
                return new ResponseEntity<>(materias, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(service.findByNombreContainingIgnoreCase(nombre), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<?>> listar() {
        try {
            return ResponseEntity.ok(service.findAll());
        } catch (Exception e) {
            return (ResponseEntity<List<?>>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Materia t, @PathVariable(value = "id")  Long id) {
            Materia current = service.findById(id).orElse(null);
            current.setNombre(t.getNombre());
            current.setDescripcion(t.getDescripcion());
            return new ResponseEntity<>(service.save(current), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    private ResponseEntity<?> eliminar(@PathVariable Long id) {
        return null;
    }

}
