package com.ista.backend.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "libreta_final")
public class Libreta_final implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5450130837865950474L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_libreta;

    @OneToMany(mappedBy = "libreta_final")
    private List<Quimestre> quimestres;

    @OneToOne
    @JoinColumn(name = "id_materia")
    private Materia materia;

    @OneToOne
    @JoinColumn(name = "id_matricula")
    private Matricula matricula;


}
