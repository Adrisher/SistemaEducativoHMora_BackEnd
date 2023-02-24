package com.ista.school.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ista.school.model.enums.Ciclo;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "curso",uniqueConstraints=@UniqueConstraint(columnNames = {"ciclo","paralelo"}) )
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_curso;

    private Integer cupo;

    private Ciclo ciclo;

    private String paralelo;

    @OneToMany(mappedBy = "curso",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Matricula> matriculas;

    @OneToMany(mappedBy = "curso",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProfesorCursoMateria> profesorCursoMaterias;

}
