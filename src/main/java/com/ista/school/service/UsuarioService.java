package com.ista.school.service;

import com.ista.school.model.entity.Usuario;

public interface UsuarioService extends BaseService<Usuario, Long> {

    Usuario logIn(String username, String password);

    Usuario findByCedula(String cedula);

}
