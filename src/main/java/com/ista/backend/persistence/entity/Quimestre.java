package com.ista.backend.persistence.entity;

import jakarta.persistence.*;

import java.io.Serializable;
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
}
