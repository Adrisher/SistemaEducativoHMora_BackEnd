package com.ista.school.controller;

import com.ista.school.model.entity.Detalle;
import com.ista.school.service.DetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hmora/matricula/detalle")
@CrossOrigin(origins = {"*"})
public class DetalleCtrl {

    @Autowired
    private DetalleService service;

    @PostMapping("/crear")
    public ResponseEntity<List<?>> crear(@RequestBody Detalle t) {
        try {
            return null;
        } catch (Exception e) {
            return null;
        }
    }



}
