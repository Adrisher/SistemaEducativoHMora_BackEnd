package com.ista.school.controller;

import com.ista.school.model.entity.Representante;
import com.ista.school.service.RepresentanteService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hmora/representante")
@CrossOrigin(origins = {"*"})
public class RepresentanteCtrl {

    @Autowired
    private RepresentanteService service;

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Representante t) {
            return ResponseEntity.ok(service.save(t));
    }

    /*@GetMapping("/listar")
    public ResponseEntity<List<?>> listar(String filtro) {
        try {
            if (filtro.isEmpty() || filtro.isBlank()) {
                return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(service.(filtro), HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonList(e.getMessage() + " Revisar los campos"));
        }
    }*/

    @PostMapping ("actualizar/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Representante t, @PathVariable Long id) {
        try {
            Representante current = service.findById(id).orElse(null);
            current.setDireccion(t.getDireccion());
            current.setOcupacion(t.getOcupacion());
            current.setCorreo(t.getCorreo());
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

    @PostMapping("/eliminar/{id}")
    private ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            Representante current = service.findById(id).orElse(null);
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

    @GetMapping("representante")
    public ResponseEntity<?> buscarRepre(@PathVariable String cedula) {
        if (StringUtils.isEmpty(cedula)) {
            return ResponseEntity.badRequest().build();
        }
        Representante repre = service.findByCedula(cedula);
        if (repre == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repre);
    }

}
