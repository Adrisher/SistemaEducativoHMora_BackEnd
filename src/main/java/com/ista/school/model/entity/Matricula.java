package com.ista.school.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "matricula", uniqueConstraints=@UniqueConstraint(columnNames = {"id_estudiante","idCurso","id_periodo"}))
public class Matricula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_matricula;

    @Column(name="fecha",updatable = false)
    @CreationTimestamp
    private LocalDateTime fecha;

    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "CAMPO VACIO")
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "id_estudiante",referencedColumnName = "id_estudiante")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_periodo",referencedColumnName = "id_periodo")
    private Periodo periodo;

    @ManyToOne
    @JoinColumn(name = "idCurso",referencedColumnName = "idCurso")
    private Curso curso;

    @OneToMany(mappedBy = "matricula",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Detalle> detalles;

}