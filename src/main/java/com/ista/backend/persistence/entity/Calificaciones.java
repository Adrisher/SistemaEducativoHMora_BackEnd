package com.ista.backend.persistence.entity;

import jakarta.persistence.*;


import java.util.Date;
import lombok.Data;

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

    @ManyToOne
    @JoinColumn(name = "id_parcial")
    private Parcial parcial;

}
