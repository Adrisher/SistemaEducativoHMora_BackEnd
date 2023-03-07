package com.ista.school.controller;

import com.ista.school.model.entity.Profesor;
import com.ista.school.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> crear(@RequestBody Profesor t) {
        Profesor esxiste = service.findByCedula(t.getCedula());
        if (esxiste != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Profesor existente!!");
        } else {
            return new ResponseEntity<>(service.save(t), HttpStatus.CREATED);
        }
    }

    @GetMapping("/buscar/")
    public ResponseEntity<List<?>> buscar(@RequestParam(name = "cedula") String cedula) {
        try {
            if (cedula.isEmpty() || cedula.isBlank()) {
                return new ResponseEntity<>(service.findByTrue(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(service.findByCedulaTrue(cedula), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<?>> listar() {
        try {
           return ResponseEntity.ok(service.findByTrue());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Profesor t, @PathVariable Long id) {
        Profesor current = service.findById(id).orElse(null);
        current.setNombre(t.getNombre());
        current.setSegundo_nombre(t.getSegundo_nombre());
        current.setPrimer_apellido(t.getPrimer_apellido());
        current.setSegundo_apellido(t.getSegundo_apellido());
        current.setArea(t.getArea());
        current.setCedula(t.getCedula());
        current.setFecha_nacimiento(t.getFecha_nacimiento());
        current.setCorreo(t.getCorreo());
        current.setDireccion(t.getDireccion());
        current.setGenero(t.getGenero());
        current.setEstado(true);
        current.setUsuario(t.getUsuario());
        return new ResponseEntity<>(service.save(current), HttpStatus.OK);
    }

    @PutMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@RequestBody Profesor t, @PathVariable(value = "id")  Long id) {
        Profesor current = service.findById(id).orElse(null);
        current.setEstado(false);
        return new ResponseEntity<>(service.save(current), HttpStatus.OK);

    }

    @GetMapping("/hired/")
    public ResponseEntity<?> buscarProfe(@RequestParam(name = "cedula") String cedula) {
        try {
            Profesor profesor = service.findByCedula(cedula);
            if (profesor == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO EXISTE");
            }
            return ResponseEntity.ok(profesor);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}