package com.ista.backend.service;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.mapper.ProfesorDTOToProfesor;
import com.ista.backend.persistence.entity.Profesor;
import com.ista.backend.persistence.enums.SexoStatus;
import com.ista.backend.persistence.repository.ProfesorRepository;
import com.ista.backend.service.dto.ProfesorDTO;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository profesorRepository;
    private final String UPLOAD_DIR="main/resources/upload";

    private final ProfesorDTOToProfesor mapper;


    public ProfesorServiceImpl(ProfesorRepository profesorRepository, ProfesorDTOToProfesor mapper ) {
        this.profesorRepository = profesorRepository;
        this.mapper=mapper;
    }

    @Override
    public Iterable<Profesor> listarTodo() {
        return this.profesorRepository.findAll();
    }

    @Override
    public Page<Profesor> listarTodo(Pageable pageable) {
        return this.profesorRepository.findAll(pageable);
    }

    @Override
    public Optional<Profesor> buscarPorId(Long id) {
        return this.profesorRepository.findById(id);
    }

    @Override
    public List<Profesor> listarPorGenero(SexoStatus status) {
        return this.profesorRepository.findAllByGenero(status);
    }

    @Override
    public Profesor guardar(Profesor profesor) {
        return this.profesorRepository.save(profesor);
    }



    @Override
    public void borrarPorId(Long id) {
        Optional<Profesor> optional=this.profesorRepository.findById(id);
        if (optional.isEmpty()){
            throw new SistemaEducativoExceptions("profesor no encontrado", HttpStatus.NOT_FOUND);
        }
        this.profesorRepository.deleteById(id);
    }

    @Override
    public void uploadFile(InputStream inputStream) {
        try {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            Path path = Paths.get(UPLOAD_DIR + "/" + UUID.randomUUID().toString());
            Files.write(path, bytes);
        } catch (IOException e) {
            throw new SistemaEducativoExceptions("error", HttpStatus.OK);
        }

    }


}
