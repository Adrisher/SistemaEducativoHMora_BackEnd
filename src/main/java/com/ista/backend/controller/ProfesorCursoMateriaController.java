package com.ista.backend.controller;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Curso;
import com.ista.backend.persistence.entity.Materia;
import com.ista.backend.persistence.entity.Profesor;
import com.ista.backend.persistence.entity.ProfesorCursoMateria;
import com.ista.backend.persistence.enums.CicloStatus;
import com.ista.backend.persistence.enums.MateriaStatus;
import com.ista.backend.persistence.enums.ParaleloStatus;
import com.ista.backend.service.CursoService;
import com.ista.backend.service.MateriaService;
import com.ista.backend.service.ProfesorCursoMateriaService;
import com.ista.backend.service.ProfesorService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hmora/materiaCursoProfesor")
public class ProfesorCursoMateriaController {

    private final CursoService cursoService;
    private final ProfesorService profesorService;
    private final MateriaService materiaService;
    private final ProfesorCursoMateriaService profesorCursoMateriaService;

    public ProfesorCursoMateriaController(CursoService cursoService, ProfesorService profesorService, MateriaService materiaService, ProfesorCursoMateriaService profesorCursoMateriaService) {
        this.cursoService = cursoService;
        this.profesorService = profesorService;
        this.materiaService = materiaService;
        this.profesorCursoMateriaService = profesorCursoMateriaService;
    }


    @PostMapping("/agregarCursoPorProfesor/{cedulaProfesor}/{cicloStatus}/{paraleloStatus}/{materiaStatus}/curso")
    public ResponseEntity<ProfesorCursoMateria> crearCursoPorProfesor(@PathVariable(value = "cedulaProfesor")String cedulaProfesor,
                                                                      @PathVariable("cicloStatus") CicloStatus cicloStatus,
                                                                      @PathVariable("paraleloStatus") ParaleloStatus paraleloStatus,
                                                                      @PathVariable("materiaStatus")MateriaStatus materiaStatus){
        ProfesorCursoMateria profesorCursoMateria= new ProfesorCursoMateria();

        Optional<Profesor> profesor=this.profesorService.buscarPorCedula(cedulaProfesor);
        if (profesor.isEmpty()){
            throw new SistemaEducativoExceptions("Profesor no encontrado",HttpStatus.NOT_FOUND);
        }
        Profesor profesor1=profesor.get();

        Curso curso=new Curso();
        curso.setCiclo(cicloStatus);
        curso.setParalelo(paraleloStatus);
        cursoService.guardar(curso);

        Materia materia=new Materia();
        materia.setMateriaStatus(materiaStatus);
        materiaService.guardar(materia);


         profesorCursoMateria.setCurso(curso);
         profesorCursoMateria.setMateria(materia);
         profesorCursoMateria.setProfesor(profesor1);
        this.profesorCursoMateriaService.guardar(profesorCursoMateria);
        return new ResponseEntity<>(profesorCursoMateria, HttpStatus.CREATED);
    }

    @GetMapping("ListarCursosMateriaPorProfesor/{cedula}")
    public List<?> listarCursos(@PathVariable("cedula")String cedula){
        return this.profesorCursoMateriaService.listarCursoMateriaPorProfesor(cedula);
    }



}
