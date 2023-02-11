package com.ista.backend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


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

    private String abreviatura;
    private String descripcion;
    private String area;

    @ManyToOne
    @JoinColumn(name="id_detalle",referencedColumnName = "id_detalle")
    @JsonIgnore
    private Matricula_detalle matriculaDetalle;



}
