package com.ista.school.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "profesor")
public class Profesor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_profesor;

    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "LLENAR EL CAMPO")
    @Column(name = "cedula", unique = true)
    private String cedula;

    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "LLENAR EL CAMPO")
    private String nombre;
    private String segundo_nombre;
    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "LLENAR EL CAMPO")
    private String primerApellido;
    private String segundo_apellido;
    private String genero;

    @Temporal(TemporalType.DATE)
    @Past
    private Date fecha_nacimiento;
    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "LLENAR EL CAMPO")
    private String correo;
    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "LLENAR EL CAMPO")
    private String direccion;
    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean estado;
    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "LLENAR EL CAMPO")
    private String area;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProfesorCursoMateria> profesorCursoMaterias;

}
