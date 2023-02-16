package com.ista.backend.controller;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Estudiante;
import com.ista.backend.persistence.entity.Profesor;
import com.ista.backend.persistence.entity.Usuario;
import com.ista.backend.persistence.enums.CargoStatus;
import com.ista.backend.service.EstudianteService;
import com.ista.backend.service.ProfesorService;
import com.ista.backend.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/hmora/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final EstudianteService estudianteService;
    private final ProfesorService profesorService;

    public UsuarioController(UsuarioService usuarioService, EstudianteService estudianteService, ProfesorService profesorService) {
        this.usuarioService = usuarioService;
        this.estudianteService = estudianteService;
        this.profesorService = profesorService;
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario){
        Optional<Usuario> oUsuario =this.usuarioService.buscarPorUsername(usuario.getNombreUsuario());
        if (oUsuario.isPresent()){
            throw new SistemaEducativoExceptions("Usuario ya registrado",HttpStatus.FOUND);
        }
        Optional<Profesor> oProfesor=this.profesorService.existsByCedula(usuario.getNombreUsuario());
        if (oProfesor.isEmpty()){
            Optional<Estudiante> oEstudiante=this.estudianteService.buscarPorCedula(usuario.getNombreUsuario());
            if (oEstudiante.isEmpty()){
                throw new SistemaEducativoExceptions("NO SE ENCUENTRA REGISTRADO BDE",HttpStatus.NOT_FOUND);
            }
            usuario.setNombreUsuario(oEstudiante.get().getCedula());
            usuario.setPasword(usuario.getPasword());
            usuario.setRol(CargoStatus.ESTUDIANTE);
            oEstudiante.get().setUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(this.usuarioService.guardar(usuario));
        }
        usuario.setNombreUsuario(oProfesor.get().getCedula());
        usuario.setPasword(usuario.getPasword());
        usuario.setRol(CargoStatus.DOCENTE);
        oProfesor.get().setUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.usuarioService.guardar(usuario));
    }

    @PostMapping("/ingreso")
    public ResponseEntity<?> ingresar(@RequestBody Usuario usuario){
        Optional<Usuario> oUsuario =this.usuarioService.buscarPorUsername(usuario.getNombreUsuario());
        if (oUsuario.isEmpty()){
            throw new SistemaEducativoExceptions("Usuario no Registrado",HttpStatus.NOT_FOUND);
        }
        if(oUsuario.get().getPasword().equals(usuario.getPasword())){
            return ResponseEntity.ok(oUsuario);
        }else{
            throw new SistemaEducativoExceptions("Contraseña Erronea",HttpStatus.NOT_ACCEPTABLE);
        }
    }


}
