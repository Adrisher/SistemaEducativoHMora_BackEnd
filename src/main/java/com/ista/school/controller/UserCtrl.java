package com.ista.school.controller;

import com.ista.school.model.entity.Usuario;
import com.ista.school.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/hmora/usuario")
@CrossOrigin(origins = {"http://localhost:4200"})
public class UserCtrl {

    @Autowired
    private UsuarioService service;

    @GetMapping("/logIn")
    public ResponseEntity<?> iniciar(String username, String password) {
        try {
            return ResponseEntity.ok(service.logIn(username, password));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage() + "Usuario no encontrado");
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario){
        try {
            Usuario current = service.findByCedula(usuario.getNombreUsuario());
            if (usuario != null) {
                return ResponseEntity.ok(service.save(usuario));
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body("Usuario existente");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos erroneos");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody Usuario u, @PathVariable Long id) {
        try {
            Usuario current = service.findById(id).orElse(null) ;
            if (current == null || u.getContraseña().isEmpty() || u.getContraseña().isBlank() ||
                    u.getNombreUsuario().isBlank() || u.getNombreUsuario().isEmpty())  {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no existente");
            } else {
                current.setContraseña(u.getContraseña());
                return ResponseEntity.accepted().body(service.save(current));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Datos erroneos");
        }
    }

}
