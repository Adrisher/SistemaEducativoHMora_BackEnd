package com.ista.backend.persistence.entity;

import com.ista.backend.persistence.enums.CargoStatus;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "usuario")
public class Usuario implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_usuario;

    @Column(unique = true)
    String nombreUsuario;
    String pasword;
    CargoStatus rol;
}
