package com.ista.school.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    @Column(unique = true)
    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "materia",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProfesorCursoMateria> profesorCursoMaterias;

    @OneToMany(mappedBy = "materia",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Detalle> detalles;

}
