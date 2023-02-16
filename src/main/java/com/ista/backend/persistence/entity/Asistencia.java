package com.ista.backend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private static final long serialVersionUID = -5450130837865950474L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private CicloStatus curso;
    private Integer faltas;

    @Column(name="fecha",unique = true)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    private AsistenciaStatus asitencia;

    @ManyToOne
    @JoinColumn(name = "id_estudiante",referencedColumnName = "id_estudiante")
    @JsonIgnore
    private Estudiante estudiante;
}
