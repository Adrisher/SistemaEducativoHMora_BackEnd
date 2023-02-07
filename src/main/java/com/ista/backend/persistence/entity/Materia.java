package com.ista.backend.persistence.entity;

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
}
