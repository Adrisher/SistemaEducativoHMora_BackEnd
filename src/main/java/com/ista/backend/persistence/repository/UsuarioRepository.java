package com.ista.backend.persistence.repository;

import com.ista.backend.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    public Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
