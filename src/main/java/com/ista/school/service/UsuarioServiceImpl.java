package com.ista.school.service;

import com.ista.school.model.entity.Usuario;
import com.ista.school.repository.BaseRepository;
import com.ista.school.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioServiceImpl(BaseRepository<Usuario, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public Usuario logIn(String username, String password) {
        return repository.findByNombreUsuarioAndContrase├▒a(username, password);
    }

    @Override
    public Usuario findByCedula(String cedula) {
        return repository.findUsuarioByNombreUsuario(cedula);
    }
}
