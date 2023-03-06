package com.ista.school.controller;

import com.ista.school.model.entity.Usuario;
import com.ista.school.service.EstudianteService;
import com.ista.school.service.ProfesorService;
import com.ista.school.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/hmora/usuario")
@CrossOrigin(origins = {"*"})
public class UserCtrl {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ProfesorService profesorService;
    @Autowired
    private EstudianteService estudianteService;

    @PostMapping("/registro/")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
            return  ResponseEntity.ok(usuarioService.save(usuario));
    }

    @PostMapping("/registro/estudiante/")
    public ResponseEntity<?> registrarEstu(@RequestBody Usuario usuario) {
        return  ResponseEntity.ok(usuarioService.save(usuario));
    }

    @GetMapping("/logIn/{username}/{password}")
    public ResponseEntity<?> iniciar(@PathVariable String username, @PathVariable String password) {
        try {
            Usuario usuario = usuarioService.logIn(username, password);
            if(usuario != null) {
                if(!profesorService.isActive(username) && !estudianteService.isActive(username)) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Se debe activar la cuenta");
                }
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Datos erroneos");
        }
    }

    /*@PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario){
        try {
            Usuario current = usuarioService.findByCedula(usuario.getNombreUsuario());
            if (usuario != null) {
                return ResponseEntity.ok(usuarioService.save(usuario));
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body("Usuario existente");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos erroneos");
        }
    }*/

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody Usuario u, @PathVariable Long id) {
        try {
            Usuario current = usuarioService.findById(id).orElse(null) ;
            if (current == null || u.getContraseña().isEmpty() || u.getContraseña().isBlank() ||
                    u.getNombreUsuario().isBlank() || u.getNombreUsuario().isEmpty())  {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no existente");
            } else {
                current.setContraseña(u.getContraseña());
                return ResponseEntity.accepted().body(usuarioService.save(current));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Datos erroneos");
        }
    }

}

