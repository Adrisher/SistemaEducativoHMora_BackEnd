package com.ista.backend.controller;

import com.ista.backend.persistence.entity.Representante;
import com.ista.backend.persistence.enums.SexoStatus;
import com.ista.backend.service.RepresentanteService;
import com.ista.backend.service.dto.ActRepresentanteDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/hmora/representante")
public class RepresentanteController {

    private final RepresentanteService representanteService;

    public RepresentanteController(RepresentanteService representanteService) {
        this.representanteService = representanteService;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearRepresentante(@RequestBody Representante representante){
            return ResponseEntity.status(HttpStatus.CREATED).body(this.representanteService.guardar(representante));
    }

    @GetMapping("/listarRespresentantes")
    public List<Representante>  listarTodo(){
        List<Representante> representantes= StreamSupport.stream(this.representanteService.listarTodo().spliterator(),false).collect(Collectors.toList());
        return representantes;
    }

    @GetMapping("/buscarId/{id}")
    public ResponseEntity<?> buscar(@PathVariable(value = "id")Long id){
        Optional<Representante> oRespresentante= this.representanteService.buscarPorId(id);
        if (!oRespresentante.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oRespresentante);
    }

    @GetMapping("obtenerListaPorGenero/{status}")
    public List<Representante> listarPorGenero(@PathVariable("status")SexoStatus status){
        return this.representanteService.listarPorGenero(status);
    }

    @DeleteMapping("/borrarPorId/{id}")
    public ResponseEntity<Void> borrar(@PathVariable("id")Long id){
        this.representanteService.borrarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizarProfesor/{id}")
    public ResponseEntity<?> actualizar(@RequestBody ActRepresentanteDTO act,@PathVariable("id")Long id){
        Optional<Representante> representanteAct=this.representanteService.buscarPorId(id);
        if (!representanteAct.isPresent()){
            return ResponseEntity.notFound().build();
        }

        representanteAct.get().setCorreo(act.getCorreo());
        representanteAct.get().setDireccion(act.getDireccion());
        representanteAct.get().setOcupacion(act.getOcupacion());
        representanteAct.get().setTelefonoContacto(act.getTelefonoConracto());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.representanteService.guardar(representanteAct.get()));

    }
}