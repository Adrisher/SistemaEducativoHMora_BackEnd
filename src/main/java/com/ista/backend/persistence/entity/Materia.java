package com.ista.backend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ista.backend.persistence.enums.MateriaStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
@Table(name = "materia")
public class Materia {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_materia;

    @Column(unique = true)
    private MateriaStatus materiaStatus;
    private String descripcion;


    @ManyToOne
    @JoinColumn(name="id_detalle",referencedColumnName = "id_detalle")
    @JsonIgnore
    private Matricula_detalle matriculaDetalle;

    @OneToMany(mappedBy = "materia",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProfesorCursoMateria> profesorCursoMaterias;




}
