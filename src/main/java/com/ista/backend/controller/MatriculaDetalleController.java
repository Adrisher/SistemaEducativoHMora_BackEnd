package com.ista.backend.controller;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Estudiante;
import com.ista.backend.persistence.entity.Matricula_detalle;
import com.ista.backend.service.MateriaService;
import com.ista.backend.service.Matricula_detalleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/hmora/detalleMatricula")
public class MatriculaDetalleController {

    private final Matricula_detalleService detalleService;
    private final MateriaService materiaService;

    public MatriculaDetalleController(Matricula_detalleService detalleService, MateriaService materiaService) {
        this.detalleService = detalleService;
        this.materiaService = materiaService;
    }

    @PostMapping("/crearDetalle")
    public ResponseEntity<?> crearDetalle(@RequestBody Matricula_detalle detalle){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.detalleService.guardar(detalle));
    }
    @GetMapping("/listarDetalles")
    public List<Matricula_detalle> listarTodo(){
        List<Matricula_detalle> detalles= StreamSupport.stream(this.detalleService.listarTodo().spliterator(),false).collect(Collectors.toList());
        return detalles;
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id")Long id,
                                        @RequestBody Matricula_detalle detalle){
        Optional<Matricula_detalle> oDetalle=this.detalleService.buscarPorId(id);
        if  (!oDetalle.isPresent()){
            throw new SistemaEducativoExceptions("No se encuentra",HttpStatus.NOT_FOUND);
        }
        oDetalle.get().setGracia(detalle.getGracia());
        oDetalle.get().setSupletorio(detalle.getSupletorio());
        oDetalle.get().setRemedial(detalle.getRemedial());
        oDetalle.get().setPromedio_final(detalle.getPromedio_final());
        return ResponseEntity.status(HttpStatus.CREATED).body(this.detalleService.guardar(oDetalle.get()));
    }

}
