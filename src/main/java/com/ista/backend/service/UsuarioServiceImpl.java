package com.ista.backend.service;

import com.ista.backend.exceptions.SistemaEducativoExceptions;
import com.ista.backend.persistence.entity.Usuario;
import com.ista.backend.persistence.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Iterable<Usuario> listarTodo() {
        return this.usuarioRepository.findAll();
    }

    @Override
    public Page<Usuario> listatodo(Pageable pageable) {
        return this.usuarioRepository.findAll(pageable);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return this.usuarioRepository.findById(id);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

    @Override
    public void borrarPorId(Long id) {
        Optional<Usuario> optional=this.usuarioRepository.findById(id);
        if (optional.isEmpty()){
            throw new SistemaEducativoExceptions("Usuario no existe", HttpStatus.NOT_FOUND);
        }
        this.usuarioRepository.deleteById(id);

    }

    @Override
    public Optional<Usuario> buscarPorUsername(String username) {
        return this.usuarioRepository.findByNombreUsuario(username);
    }
}
