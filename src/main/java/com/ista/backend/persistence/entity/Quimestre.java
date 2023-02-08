package com.ista.backend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Entity
@Data
@Table(name = "quimestre")
public class Quimestre implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_quimestre;

    private Double promedioParcial;
    private Double prom_ochenta;
    private Double examen_quimestral;
    private Double nota_final;

    @ManyToOne
    @JoinColumn(name = "id_libreta",referencedColumnName = "id_libreta")
    @JsonIgnore
    private Libreta_final libreta_final;

    @JsonIgnore
    @OneToMany(mappedBy = "quimestre",cascade = CascadeType.ALL)
    private List<Parcial> parciales;



}
