package com.ista.backend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "materia")
public class Materia {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_materia;

    private String abreviatura;
    private String descripcion;
    private String area;

    @OneToOne
    @JoinColumn(name = "id_libreta")
    @JsonIgnore
    private Libreta_final libretaFinal;

    @OneToOne
    @JoinColumn(name = "matricula_detalle_id_detalle")
    @JsonIgnore
    private Matricula_detalle matriculaDetalle;

    @ManyToOne
    @JoinColumn(name="id_profesor",referencedColumnName = "id_profesor")
    @JsonIgnore
    private Profesor profesor;


}
