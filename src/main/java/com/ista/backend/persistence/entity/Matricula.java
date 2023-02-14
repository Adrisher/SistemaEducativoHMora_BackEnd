package com.ista.backend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

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


    @Column(name="fecha",updatable = false)
    @CreationTimestamp
    private LocalDateTime fecha;

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

    @OneToMany(mappedBy = "matricula",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Matricula_detalle> detalles;




}
