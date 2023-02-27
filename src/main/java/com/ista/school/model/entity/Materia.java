package com.ista.school.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "materia")
public class Materia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_materia;
    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "No valido") @Pattern(regexp = "[A-Z0-9 ]+")
    @Column(unique = true)
    private String nombre;
    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "No valido")
    private String descripcion;

    @OneToMany(mappedBy = "materia",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProfesorCursoMateria> profesorCursoMaterias;

    @OneToMany(mappedBy = "materia",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Detalle> detalles;

}
