package com.ista.school.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ista.school.model.enums.Genero;
import jakarta.persistence.*;
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

    @Column(name = "cedula",unique = true)
    private String cedula;

    private String nombre;
    private String segundo_nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private Genero genero;
    private Date fecha_nacimiento;
    private String correo;
    private String direccion;
    private Boolean estado;
    private String ocupacion;
    private String telefonoContacto;
    private String telefonoContacto2;

    @OneToMany(mappedBy = "representante",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Estudiante> estudiantes;

}
