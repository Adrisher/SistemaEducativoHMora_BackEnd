package com.ista.backend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ista.backend.persistence.enums.CicloStatus;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

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

    @ManyToOne
    @JoinColumn(name = "id_estudiante",referencedColumnName = "id_estudiante")
    @JsonIgnore
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_periodo",referencedColumnName = "id_periodo")
    @JsonIgnore
    private Periodo periodo;

    @ManyToOne
    @JoinColumn(name = "id_curso",referencedColumnName = "id_curso")
    @JsonIgnore
    private Curso curso;

    @OneToOne
    @JoinColumn(name = "id_libreta",referencedColumnName = "id_libreta")
    @JsonIgnore
    private Libreta_final libretaFinal;

    @OneToOne
    @JoinColumn(name = "id_detalle",referencedColumnName = "id_detalle")
    @JsonIgnore
    private Matricula_detalle matriculaDetalle;

}
