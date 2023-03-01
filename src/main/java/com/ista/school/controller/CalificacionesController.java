package com.ista.school.controller;


import com.ista.school.model.entity.Calificacion;
import com.ista.school.model.entity.Estudiante;
import com.ista.school.service.CalificacionService;
import com.ista.school.service.MatriculaService;
import com.ista.school.service.ProfesorCursoMateriaService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hmora/calificaciones")
@CrossOrigin(origins = {"*"})
public class CalificacionesController {

    @Autowired
    private CalificacionService calificaion;
    @Autowired
    private MatriculaService matriculaService;


    @PostMapping("/crearActualizar")
    public ResponseEntity<?> crearActualizar(@RequestBody Calificacion c) {
        System.out.println("ok");

        try {
            Optional<Calificacion> find = calificaion.findById(c.getId_calificacion());
            if (!find.isEmpty()) {
                System.out.println("encontrado");
                find.get().setCalificacion(c.getCalificacion());
                find.get().setDescripcion(c.getDescripcion());
                return new ResponseEntity<>(calificaion.save(find.get()), HttpStatus.OK);
            }
            System.out.println("creando");
            return new ResponseEntity<>(calificaion.save(c), HttpStatus.OK);
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
    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Calificacion c) {
        try{
            return new ResponseEntity<>(calificaion.save(c),HttpStatus.OK);
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

    @GetMapping("/listar")
    public ResponseEntity<List<?>> listar(){
        try {
            return new ResponseEntity<>(calificaion.findAll(),HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonList(e.getMessage()+"Revisar los campos"));
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Calificacion c,
                                        @PathVariable Long id) {
        try {
            Optional<Calificacion> update = calificaion.findById(id);
            update.get().setCalificacion(c.getCalificacion());
            update.get().setDescripcion(c.getDescripcion());
            return new ResponseEntity<>(calificaion.save(update.get()), HttpStatus.OK);
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
