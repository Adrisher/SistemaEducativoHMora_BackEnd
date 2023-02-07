package com.ista.backend.persistence.entity;

import com.ista.backend.persistence.enums.AsistenciaStatus;
import jakarta.persistence.*;


import java.util.Date;
import lombok.Data;

@Entity
@Data
@Table(name = "asistencia")
public class Asistencia {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String estudiante;
    private String curso;

    @Column(name="fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    private AsistenciaStatus asistencia;
}
