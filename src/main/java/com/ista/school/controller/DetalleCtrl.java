package com.ista.school.controller;

import com.ista.school.model.entity.Detalle;
import com.ista.school.service.DetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hmora/matricula/detalle")
@CrossOrigin(origins = "http://localhost:4200")
public class DetalleCtrl {

    @Autowired
    private DetalleService service;

    @PostMapping("/crear")
    public ResponseEntity<List<Detalle>> crear(@RequestBody List<Detalle> detalles) {
            List<Detalle> detallesGuardados = service.saveAll(detalles);
            return ResponseEntity.ok(detallesGuardados);

    }

    @PostMapping("/insertar")
    public ResponseEntity<?> insertar(@RequestBody Detalle detalles) {
        Detalle detallesGuardados = service.save(detalles);
        return ResponseEntity.ok(detallesGuardados);
    }





}
