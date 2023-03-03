package com.ista.school.controller;

import com.ista.school.model.entity.Profesor;
import com.ista.school.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hmora/profesor")
@CrossOrigin(origins = {"*"})
public class ProfesorCtrl {

    @Autowired
    private ProfesorService service;

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Profesor t) {
        Profesor esxiste = service.findByCedula(t.getCedula());
        if (esxiste != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Periodo existente!!");
        } else {
            return new ResponseEntity<>(service.save(t), HttpStatus.CREATED);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<?>> listar(String nombre) {
        if (!nombre.trim().isEmpty()) {
            try {
                List<Profesor> profesores = service.findAll();
                return new ResponseEntity<>(profesores, HttpStatus.OK);
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

    @PutMapping("actualizar/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Profesor t, @PathVariable(value = "id")  Long id) {
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
        return new ResponseEntity<>(service.save(current), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    private RequestEntity<?> eliminar(@PathVariable Long id) {
        return service.deleteById(id);

    }


}
