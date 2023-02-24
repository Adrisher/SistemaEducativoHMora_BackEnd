package com.ista.school.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "quimestre")
public class Quimestre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_quimestre;

    private Double promedioParcial;
    private Double examen_quimestral;



    @JsonIgnore
    @OneToMany(mappedBy = "quimestre",cascade = CascadeType.ALL)
    private List<Parcial> parciales;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_detalle",referencedColumnName = "id_detalle")
    private Detalle detalle;

}
