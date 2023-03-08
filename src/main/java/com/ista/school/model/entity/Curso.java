package com.ista.school.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "curso",uniqueConstraints=@UniqueConstraint(columnNames = {"ciclo","paralelo"}) )
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCurso;

    @Column(columnDefinition = "INTEGER CHECK (cupo >= 0)")
    private Integer cupo;

    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "Se debe ingresar el curso")
    @Column(columnDefinition = "VARCHAR(50) CHECK (ciclo ~ '^[A-Z]*$')")
    private String ciclo;

    @NotNull(message = "Campo Obligatorio") @NotBlank(message = "Se debe ingresar el paralelo")
    @Column(columnDefinition = "CHAR(1) CHECK (paralelo ~ '^[A-Z]$')")
    private String paralelo;

    @OneToMany(mappedBy = "curso",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Matricula> matriculas;

    @OneToMany(mappedBy = "curso",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProfesorCursoMateria> profesorCursoMaterias;

}
