package com.ista.backend.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "calificaciones")
public class Calificaciones {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_calificacion;

    private Double calificacion;
    private String descripcion;

    @Column(name="fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

}
