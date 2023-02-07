package com.ista.backend.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
@Table(name = "parcial")
public class Parcial {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_parcial;

    private Double prueba_parcial;
    private Double promedio;

    @ManyToOne
    @JoinColumn(name = "id_quimestre")
    private Quimestre quimestre;

    @OneToMany(mappedBy = "parcial")
    private List<Calificaciones> calificaciones;

}
