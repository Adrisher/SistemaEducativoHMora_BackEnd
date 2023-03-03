package com.ista.school.repository;

import com.ista.school.model.entity.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {

    Usuario findByNombreUsuarioAndContraseña(String username, String password);

    Usuario findUsuarioByNombreUsuario(String username);

}
