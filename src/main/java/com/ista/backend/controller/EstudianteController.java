package com.ista.backend.controller;

import com.ista.backend.persistence.entity.Estudiante;
import com.ista.backend.persistence.enums.SexoStatus;
import com.ista.backend.service.EstudianteService;
import com.ista.backend.service.RepresentanteService;
import com.ista.backend.service.dto.ActEstudianteDTO;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/hmora/estudiante")
public class EstudianteController {

    private final EstudianteService estudianteService;
    private final RepresentanteService representanteService;


    public EstudianteController(EstudianteService estudianteService, RepresentanteService representanteService) {
        this.estudianteService = estudianteService;
        this.representanteService = representanteService;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearEstudiante(@RequestBody Estudiante estudiante){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.estudianteService.guardar(estudiante));
    }

    @GetMapping("/lista_estudiantes")
    public List<Estudiante> listarTodo(){
        List<Estudiante> estudiantes= StreamSupport.stream(this.estudianteService.listarTodo().spliterator(),false).collect(Collectors.toList());
        return estudiantes;
    }

    @GetMapping("/buscarId/{id}")
    public ResponseEntity<?> buscar(@PathVariable(value = "id")Long id){
        Optional<Estudiante> oEstudiante=this.estudianteService.buscarPorId(id);
        if (!oEstudiante.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oEstudiante);
    }

    @GetMapping("/obtenerListaPorGenero/{status}")
    public List<Estudiante> listarPorGenero(@PathVariable("status")SexoStatus status){
        return this.estudianteService.listarPorGenero(status);
    }

    @DeleteMapping("/borrarPorId/{id}")
    public ResponseEntity<Void> borrar(@PathVariable("id")Long id){
        this.estudianteService.borrarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizarEstudiante/{id}")
    public ResponseEntity<?> actualizar(@RequestBody ActEstudianteDTO act,@PathVariable(value = "id")Long id){
        Optional<Estudiante> estudianteAct=this.estudianteService.buscarPorId(id);
        if (!estudianteAct.isPresent()){
            return ResponseEntity.notFound().build();
        }
        estudianteAct.get().setCorreo(act.getCorreo());
        estudianteAct.get().setDireccion(act.getDireccion());
        estudianteAct.get().setContraseña(act.getContraseña());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.estudianteService.guardar(estudianteAct.get()));
    }

    @PostMapping("/representante/{id_representante}/estudiantes")
    public ResponseEntity<Estudiante> crearEstudiante(@PathVariable(value = "id_representante")Long id_representante,
                                                      @RequestBody Estudiante estudiante){
        Estudiante estudiante1=this.representanteService.buscarPorId(id_representante).map(representante -> {
            estudiante.setRepresentante(representante);
            return  this.estudianteService.guardar(estudiante);
        }).orElseThrow();

        return new ResponseEntity<>(estudiante1,HttpStatus.CREATED);

    }

    @GetMapping("/representante/{id_representante}/estudiantes")
    public ResponseEntity<List<Estudiante>> listaEstudiantesPorRepresentante(@PathVariable(value = "id_representante")Long id_representante){
        if(!this.representanteService.existsById(id_representante)){
            throw new OpenApiResourceNotFoundException("no se encontro");
        }
        List<Estudiante> estudiantes=this.estudianteService.findAllByRepresentante(id_representante);
        return new ResponseEntity<>(estudiantes,HttpStatus.OK);

    }
}
