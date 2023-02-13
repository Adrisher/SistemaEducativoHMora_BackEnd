package com.ista.backend.controller;

import com.ista.backend.context.CedulaValidador;
import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Estudiante;
import com.ista.backend.persistence.entity.Representante;
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
    private  CedulaValidador validador=new CedulaValidador();


    public EstudianteController(EstudianteService estudianteService, RepresentanteService representanteService) {
        this.estudianteService = estudianteService;
        this.representanteService = representanteService;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearEstudiante(@RequestBody Estudiante estudiante){
        if (validador.validadorDeCedula(estudiante.getCedula())){
            return ResponseEntity.status(HttpStatus.CREATED).body(this.estudianteService.guardar(estudiante));
        }
        throw new SistemaEducativoExceptions("cedula no valida ",HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/lista_estudiantes")
    public List<Estudiante> listarTodo(){
        List<Estudiante> estudiantes= StreamSupport.stream(this.estudianteService.listarTodo().spliterator(),false).collect(Collectors.toList());
        return estudiantes;
    }

    @GetMapping("/buscarId/{cedula}")
    public ResponseEntity<?> buscar(@PathVariable(value = "cedula")String cedula){
        Optional<Estudiante> oEstudiante=this.estudianteService.buscarPorCedula(cedula);
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

    @PutMapping("/actualizarEstudiante/{cedula}")
    public ResponseEntity<?> actualizar(@RequestBody ActEstudianteDTO act,@PathVariable(value = "cedula")String cedula){
        Optional<Estudiante> estudianteAct=this.estudianteService.buscarPorCedula(cedula);
        if (!estudianteAct.isPresent()){
            return ResponseEntity.notFound().build();
        }
        estudianteAct.get().setCorreo(act.getCorreo());
        estudianteAct.get().setDireccion(act.getDireccion());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.estudianteService.guardar(estudianteAct.get()));
    }

    @PostMapping("/CrearEstudiantePorRepresentante/{cedula}/estudiantes")
    public ResponseEntity<Estudiante> crearEstudiante(@PathVariable(value = "cedula")String cedula,
                                                      @RequestBody Estudiante estudiante){
        Estudiante estudiante1=this.representanteService.buscarPorCedula(cedula).map(representante -> {
            estudiante.setRepresentante(representante);
            return  this.estudianteService.guardar(estudiante);
        }).orElseThrow();

        return new ResponseEntity<>(estudiante1,HttpStatus.CREATED);

    }

    @GetMapping("/Listar estudiantePorRepresentante /{cedula}/estudiantes")
    public ResponseEntity<List<Estudiante>> listaEstudiantesPorRepresentante(@PathVariable(value = "cedula")String cedula){
        if(!this.representanteService.existsByCedula(cedula)){
            throw new OpenApiResourceNotFoundException("no se encontro");
        }
        Optional<Representante> represet=representanteService.buscarPorCedula(cedula);

        List<Estudiante> estudiantes=represet.get().getEstudiantes();
        return new ResponseEntity<>(estudiantes,HttpStatus.OK);

    }
}
