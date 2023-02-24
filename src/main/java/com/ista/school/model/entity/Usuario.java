package com.ista.school.model.entity;

import com.ista.school.model.enums.Rol;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_usuario;

    @Column(unique = true)
    String nombreUsuario;
    String contraseña;
    Rol rol;

}
