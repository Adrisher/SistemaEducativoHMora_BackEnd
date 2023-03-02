package com.ista.school.controller;

import com.ista.school.model.entity.Curso;
import com.ista.school.service.CursoService;
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
@RequestMapping("/hmora/curso")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CursoCtrl {
    @Autowired
    private CursoService service;

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Curso t) {
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

    @GetMapping("/buscar/{filtro}")
    public ResponseEntity<List<?>> buscar(@PathVariable String filtro) {
        try {
            if (filtro.isEmpty() || filtro.isBlank()) {
                return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(service.findByCicloOrParalelo(filtro), HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonList(e.getMessage() + " Revisar los campos"));
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<?>> listar() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping ("actualizar/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Curso t, @PathVariable Long id) {
        try {
            Curso current = service.findById(id).orElse(null);
            current.setCiclo(t.getCiclo());
            current.setCupo(t.getCupo());
            current.setParalelo(t.getParalelo());
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

    @DeleteMapping("/eliminar/{id}")
    private ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Se elimino correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage() + " Error de constraint");
        }
    }

}
