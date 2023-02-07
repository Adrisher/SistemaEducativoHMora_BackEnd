package com.ista.backend.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "materia_curso_profesor")
public class Materia_Curso_Profesor implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5450130837865950474L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_materia_curso;

    @ManyToOne
    @JoinColumn(name = "id_materia")
    private Materia materia;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;

}
