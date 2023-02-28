package com.ista.school.controller;

import com.ista.school.model.entity.Estudiante;
import com.ista.school.service.EstudianteService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/hmora/estudiante")
@CrossOrigin(origins = {"http://localhost:4200"})
public class EstudianteCtrl {

    @Autowired
    private EstudianteService service;

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Estudiante t) {
        try {
            return new ResponseEntity<>(service.save(t), HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (UnexpectedTypeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage() + " Revisar los campos");
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<?>> buscarCedular(String cedula) {
        try {
            if (cedula.isEmpty() || cedula.isBlank()) {
                return new ResponseEntity<>(service.findByEstadoTrue(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(service.findByEstadoTrueAndCedula(cedula), HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonList(e.getMessage() + " Revisar los campos"));
        }
    }
    @GetMapping("/listar")
    public ResponseEntity<List<?>> listar() {
        return new ResponseEntity<>(service.findByEstadoTrue(), HttpStatus.OK);
    }

    @PostMapping ("actualizar/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Estudiante t, @PathVariable Long id) {
        try {
            Estudiante current = service.findById(id).orElse(null);
            current.setDireccion(t.getDireccion());
            current.setCorreo(t.getCorreo());
            current.setRepresentante(t.getRepresentante());
            current.setEstado(true);
            return new ResponseEntity<>(service.save(current), HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (UnexpectedTypeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage() + " Revisar los campos");
        }
    }

    @PutMapping("/eliminar/{id}")
    private ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            Estudiante current = service.findById(id).orElse(null);
            current.setEstado(false);
            return new ResponseEntity<>(service.save(current), HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (UnexpectedTypeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage() + " Revisar los campos");
        }
    }

}
