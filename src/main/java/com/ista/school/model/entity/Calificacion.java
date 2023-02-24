package com.ista.school.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "calificacion")
public class Calificacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_calificacion;

    @ManyToOne
    @JoinColumn(name = "id_parcial",referencedColumnName = "id_parcial")
    @JsonIgnore
    private Parcial parcial;

    private Double calificacion;
    private String descripcion;

    @Column(name="fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

}
