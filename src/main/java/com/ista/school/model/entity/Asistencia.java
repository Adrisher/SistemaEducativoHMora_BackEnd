package com.ista.school.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ista.school.model.enums.Ciclo;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "asistencia")
public class Asistencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Ciclo curso;
    private Integer faltas;

    @Column(name="fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    private Boolean presente;

    @ManyToOne
    @JoinColumn(name = "id_estudiante",referencedColumnName = "id_estudiante")
    @JsonIgnore
    private Estudiante estudiante;

}
