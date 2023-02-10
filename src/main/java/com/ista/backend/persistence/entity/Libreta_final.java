package com.ista.backend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ista.backend.persistence.enums.PromovidoStatus;
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

    private double promedio;
    private PromovidoStatus promovido;

    @JsonIgnore
    @OneToMany(mappedBy = "libreta_final",cascade = CascadeType.ALL)
    private List<Quimestre> quimestres;

    @OneToOne
    @JoinColumn(name = "id_materia",referencedColumnName = "id_materia")
    @JsonIgnore
    private Materia materia;

    @OneToOne
    @JoinColumn(name = "id_matricula",referencedColumnName = "id_matricula")
    @JsonIgnore
    private Matricula matricula;


}
