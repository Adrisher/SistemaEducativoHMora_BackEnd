package com.ista.backend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.io.Serializable;
import java.util.List;

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

    @OneToMany(mappedBy = "matriculaDetalle",cascade= CascadeType.ALL)
    @JsonIgnore
    private List<Materia> materias;

    private Double supletorio;
    private Double remedial;
    private Double gracia;
    private Double promedio_final;

    @OneToMany(mappedBy ="matriculaDetalle",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Quimestre> quimestre;



}
