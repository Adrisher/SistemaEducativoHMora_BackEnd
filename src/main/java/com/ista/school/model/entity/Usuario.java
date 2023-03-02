package com.ista.school.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "No valido")
    private String nombreUsuario;
    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "No valido")
    private String contraseña;
    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "No valido")
    private String rol;

}
