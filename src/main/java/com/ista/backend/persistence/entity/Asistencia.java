package com.ista.backend.persistence.entity;

import com.ista.backend.persistence.enums.AsistenciaStatus;
import com.ista.backend.persistence.enums.CicloStatus;
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

    private CicloStatus curso;

    @Column(name="fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    private AsistenciaStatus asistencia;

    @ManyToOne
    @JoinColumn(name = "id_estudiante",referencedColumnName = "id_estudiante")
    private Estudiante estudiante;
}
