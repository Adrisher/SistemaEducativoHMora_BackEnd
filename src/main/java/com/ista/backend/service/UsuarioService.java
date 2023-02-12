package com.ista.backend.service;

import com.ista.backend.persistence.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UsuarioService {

    public Iterable<Usuario> listarTodo();
    public Page<Usuario> listatodo(Pageable pageable);
    public Optional<Usuario> buscarPorId(Long id);
    public Usuario guardar(Usuario usuario);
    public void borrarPorId(Long id);
}
