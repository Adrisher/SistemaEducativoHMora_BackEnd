package com.ista.backend.controller;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.mapper.ProfesorDTOToProfesor;
import com.ista.backend.persistence.entity.Profesor;
import com.ista.backend.persistence.enums.SexoStatus;
import com.ista.backend.service.ProfesorService;
import com.ista.backend.service.dto.ActProfesorDTO;
import com.ista.backend.service.dto.ProfesorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/hmora/profesor")
public class ProfesorController {

    private final ProfesorService profesorService;


    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @PostMapping("/upload")
    public void handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            this.profesorService.uploadFile(file.getInputStream());
        }catch (IOException e){
            throw new SistemaEducativoExceptions("error", HttpStatus.OK);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearProfesor(@RequestBody Profesor profesor){
        return  ResponseEntity.status(HttpStatus.CREATED).body(this.profesorService.guardar(profesor));
    }

    @GetMapping("/ListarProfesores")
    public List<Profesor> listarTodo(){
        List<Profesor> profesores= StreamSupport.stream(this.profesorService.listarTodo().spliterator(),false).collect(Collectors.toList());
        return profesores;
    }

    @GetMapping("/buscarId/{id}")
    public ResponseEntity<?> buscar(@PathVariable(value="id")Long id){
        Optional<Profesor> oProfesor=this.profesorService.buscarPorId(id);
        if (!oProfesor.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oProfesor);
    }

    @GetMapping("/obetenerListaGenero/{status}")
    public List<Profesor> listarPorGenero(@PathVariable("status")SexoStatus status){
        return this.profesorService.listarPorGenero(status);
    }

    @DeleteMapping("/borrarLista/{id}")
    public ResponseEntity<Void> borrar(@PathVariable("id")Long id){
        this.profesorService.borrarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizarProfesor/{id}")
    public ResponseEntity<?> actualizar(@RequestBody ActProfesorDTO dto,@PathVariable(value="id")Long id){
        Optional<Profesor> profesorAct=this.profesorService.buscarPorId(id);
        if (!profesorAct.isPresent()){
            return ResponseEntity.notFound().build();
        }
        profesorAct.get().setCorreo(dto.getCorreo());
        profesorAct.get().setDireccion(dto.getDireccion());
        profesorAct.get().setArea(dto.getArea());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.profesorService.guardar(profesorAct.get()));
    }



}
