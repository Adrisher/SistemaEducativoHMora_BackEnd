package com.ista.backend.controller;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.service.ProfesorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
}
