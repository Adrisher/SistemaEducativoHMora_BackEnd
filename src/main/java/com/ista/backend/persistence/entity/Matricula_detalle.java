package com.ista.backend.persistence.entity;

import jakarta.persistence.*;


import java.io.Serializable;
import lombok.Data;

@Entity
@Data
@Table(name = "matricula_detalle")
public class Matricula_detalle implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_detalle;

    private Double supletorio;
    private Double remedial;
    private Double gracia;
    private Double promedio;

}
