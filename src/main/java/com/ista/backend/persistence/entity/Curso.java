package com.ista.backend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ista.backend.persistence.enums.ParaleloStatus;
import jakarta.persistence.*;


import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Entity
@Data
@Table(name = "curso")
public class Curso implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_curso;

    private Integer cupo;
    private ParaleloStatus paralelo;

    @OneToMany(mappedBy = "curso")
    @JsonIgnore
    private List<Matricula> matriculas;

    @ManyToOne
    @JoinColumn(name="id_profesor",referencedColumnName = "id_profesor")
    @JsonIgnore
    private Profesor profesor;
}
