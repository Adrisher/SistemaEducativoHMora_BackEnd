package com.ista.school.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "representante")
public class Representante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_representante;

    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "No valido")
    @Column(name = "cedula",unique = true)
    private String cedula;

    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "No valido")
    private String nombre;

    private String segundo_nombre;
    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "No valido")
    private String primer_apellido;

    private String segundo_apellido;
    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "No valido")
    @Pattern(regexp = "[MF]{1}")
    private String genero;
    @Temporal(TemporalType.DATE)
    @Past
    private Date fecha_nacimiento;
    @NotNull(message = "Campo Obligatorio") @Email(message = "Invalid email address")
    private String correo;
    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "No valido")
    private String direccion;
    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "No valido")
    private String ocupacion;
    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "No valido")
    private String telefonoContacto;

    private String telefonoContacto2;
    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean estado;


    @OneToMany(mappedBy = "representante",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Estudiante> estudiantes;

}
