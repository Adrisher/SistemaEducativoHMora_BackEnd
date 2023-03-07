package com.ista.school.controller;

import com.ista.school.model.entity.Periodo;
import com.ista.school.service.PeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/hmora/periodo/")
@CrossOrigin(origins = {"*"})
public class PeriodoCtrl {

    @Autowired
    private PeriodoService service;

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Periodo t) {
        //try {
            return new ResponseEntity<>(service.save(t), HttpStatus.OK);
        //} catch (Exception e) {
        //    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en el servidor");
        //}
    }

    @GetMapping("/listar")
    public ResponseEntity<List<?>> listar() {
        try {
            return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonList("Error en el servidor"));
        }
    }

    @GetMapping("/listarPeriodos")
    public ResponseEntity<List<?>> listarPeriodos() {
        try {
            return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonList("Error en el servidor"));
        }
    }

    @PostMapping ("actualizar/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Periodo t, @PathVariable Long id) {
        try {
            Periodo current = service.findById(id).orElse(null);
            if (current != null) {
                current.setFecha_inicio(t.getFecha_inicio());
                current.setFecha_fin(t.getFecha_fin());
                return new ResponseEntity<>(service.save(current), HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Objeto no existente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" Error del Servidor");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> periodoId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage() + " Error de constraint");
        }
    }

}
