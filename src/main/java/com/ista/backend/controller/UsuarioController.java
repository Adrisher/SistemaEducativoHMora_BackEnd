package com.ista.backend.controller;

import com.ista.backend.service.EstudianteService;
import com.ista.backend.service.ProfesorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hmora/usuario")
public class UsuarioController {

    private final EstudianteService estudianteService;
    private final ProfesorService profesorService;

    public UsuarioController(EstudianteService estudianteService, ProfesorService profesorService) {
        this.estudianteService = estudianteService;
        this.profesorService = profesorService;
    }

    
}
