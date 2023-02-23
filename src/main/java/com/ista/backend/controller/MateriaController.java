package com.ista.backend.controller;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Materia;
import com.ista.backend.service.MateriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/hmora/materia")
public class MateriaController {

    private final MateriaService materiaService;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Void> crear(){
        List<Materia> materias= StreamSupport.stream(this.materiaService.listarTodo().spliterator(),false).collect(Collectors.toList());
        if (!materias.isEmpty()){
            throw new SistemaEducativoExceptions("Materias Ya existen \uD83D\uDE31",HttpStatus.FOUND);
        }
        this.materiaService.listarTodo();
        Materia ingles=new Materia();
        ingles.setMateriaDetalle("Ingles");
        this.materiaService.guardar(ingles);
        Materia matematicas=new Materia();
        matematicas.setMateriaDetalle("Matemática");
        this.materiaService.guardar(matematicas);
        Materia sociales=new Materia();
        sociales.setMateriaDetalle("Ciencias Sociales");
        this.materiaService.guardar(sociales);
        Materia artistica=new Materia();
        artistica.setMateriaDetalle("Educación Artistica");
        this.materiaService.guardar(artistica);
        Materia eduFisica=new Materia();
        eduFisica.setMateriaDetalle("Educación Fisica");
        this.materiaService.guardar(eduFisica);
        Materia ciencias=new Materia();
        ciencias.setMateriaDetalle("Ciencias_Naturales");
        this.materiaService.guardar(ciencias);
        Materia lenguaje=new Materia();
        lenguaje.setMateriaDetalle("Lengua_Literatura");
        this.materiaService.guardar(lenguaje);


        return ResponseEntity.ok().build();
    }


    @PostMapping("/ingresarMateriaNueva")
        public ResponseEntity<?> crearNuevaMateria(@RequestBody Materia materia){
        Optional<Materia> oMateria=this.materiaService.buscarPorMateria(materia.getMateriaDetalle());
        if(!oMateria.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(this.materiaService.guardar(materia));

        }
        throw new SistemaEducativoExceptions("materia ya existe", HttpStatus.NOT_FOUND);
        }

        @GetMapping("/listar")
    public List<Materia> listarMaterias(){
        List<Materia> materias=StreamSupport.stream(this.materiaService.listarTodo().spliterator(),false).collect(Collectors.toList());
        return materias;
        }


}
