package com.ista.backend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ista.backend.persistence.enums.CicloStatus;
import com.ista.backend.persistence.enums.ParaleloStatus;
import jakarta.persistence.*;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Entity
@Data
@Table(name = "curso",uniqueConstraints=@UniqueConstraint(columnNames = {"ciclo","paralelo"}) )
public class Curso implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_curso;

    private Integer cupo;

    private CicloStatus ciclo;

    private ParaleloStatus paralelo;

    @OneToMany(mappedBy = "curso",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Matricula> matriculas;

    @OneToMany(mappedBy = "curso",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProfesorCursoMateria> profesorCursoMaterias;
}
