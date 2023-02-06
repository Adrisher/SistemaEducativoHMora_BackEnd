package com.ista.backend.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "periodo")
public class Periodo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_periodo;

    @Column(name="fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fecha_inicio;

    private Date fecha_fin;
}
