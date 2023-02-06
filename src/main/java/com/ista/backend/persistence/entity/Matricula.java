package com.ista.backend.persistence.entity;

import com.ista.backend.persistence.enums.CicloStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "matricula")
public class Matricula implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_matricula;

    private CicloStatus ciclo;

    @Column(name="fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    private String observaciones;

}
